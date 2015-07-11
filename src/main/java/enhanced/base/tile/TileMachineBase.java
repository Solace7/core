package enhanced.base.tile;

import java.util.Map.Entry;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraftforge.common.util.ForgeDirection;
import cofh.api.energy.EnergyStorage;
import cofh.api.energy.IEnergyHandler;
import enhanced.core.machine.RecipeMachine;

public abstract class TileMachineBase extends TileBase implements IEnergyHandler, IInventory {
	protected ItemStack[] inventory;
    protected EnergyStorage storage;
    public ForgeDirection facing = ForgeDirection.UNKNOWN;
    public int processingTime = 0;
    protected int powerToDrain = 0;
	protected int powerDrainCost = 0;
	public float PROCESS_TIME = 100f;

    public TileMachineBase(int energy) {
        super();
        storage = new EnergyStorage(energy);
    }

    public TileMachineBase(int energy, int transferRate) {
        super();
        storage = new EnergyStorage(energy, transferRate);
    }

    public TileMachineBase(int energy, int extract, int receive) {
        super();
        storage = new EnergyStorage(energy, receive, extract);
    }

    @Override
    public void readFromNBT(NBTTagCompound nbt) {
        super.readFromNBT(nbt);
        storage.readFromNBT(nbt);
        facing = ForgeDirection.getOrientation(nbt.getByte("facing"));
        
        if (nbt.hasKey("inventory")) {
        	NBTTagList inv = nbt.getTagList("inventory", 10);
        	
        	for (int i = 0; i < inv.tagCount(); i++) {
        		ItemStack stack = ItemStack.loadItemStackFromNBT(inv.getCompoundTagAt(i));
        		
        		if (stack != null) {
        			setInventorySlotContents(i, stack);
        		}
        	}
        }
        
        loadData(nbt);
    }

    @Override
    public void writeToNBT(NBTTagCompound nbt) {
        super.writeToNBT(nbt);
        storage.writeToNBT(nbt);
        nbt.setByte("facing", (byte) facing.ordinal());
        
        if (inventory != null) {
	        NBTTagList inv = new NBTTagList();
	        
	        for (ItemStack stack : inventory) {
	        	NBTTagCompound slot = new NBTTagCompound();
	        	
	        	if (stack != null)
	        		stack.writeToNBT(slot);
	        	
	        	inv.appendTag(slot);
	        }
	        
	        nbt.setTag("inventory", inv);
        }
        
        saveData(nbt);
    }
    
    protected abstract void saveData(NBTTagCompound nbt);
    protected abstract void loadData(NBTTagCompound nbt);
    
	protected void resetCrafting() {
		processingTime = 0;
		powerToDrain = 0;
		powerDrainCost = 0;
	}

	protected int getPowerCost() {
		if (powerDrainCost == 0) {
			powerDrainCost = powerToDrain / processingTime;
		}

		return powerDrainCost;
	}
	
	protected abstract Entry<RecipeMachine, ItemStack> getRecipe();
	protected abstract boolean hasEnoughComponents(RecipeMachine recipe);
	protected abstract boolean hasEnoughExit(Entry<RecipeMachine, ItemStack> recipe);
	protected abstract void handleRecipeCompleted(Entry<RecipeMachine, ItemStack> recipe);

	@Override
	public void updateEntity() {
		if (getWorldObj().isRemote)
			return;
		
		boolean isCooking = processingTime != 0;
		Entry<RecipeMachine, ItemStack> recipe = getRecipe();
		
		if ((recipe == null && isCooking) || recipe == null) {
			resetCrafting();
			return;
		}
		
		if (isCooking) {
			if (!hasEnoughComponents(recipe.getKey())) {
				resetCrafting();
				return;
			} else if (!hasEnoughExit(recipe)) {
				return; // Wait
			}
			
			if (extractEnergy(ForgeDirection.UNKNOWN, getPowerCost(), true) == getPowerCost()) {
				extractEnergy(ForgeDirection.UNKNOWN, getPowerCost(), false);
				powerToDrain -= getPowerCost();
				processingTime--;
				
				if (processingTime <= 1) {
					handleRecipeCompleted(recipe);
				}
			}
		} else {
			if (hasEnoughComponents(recipe.getKey()) && hasEnoughExit(recipe)) { // Do we have enough components & enough exit space?
				processingTime = (int) PROCESS_TIME;
				powerToDrain = recipe.getKey().energy;
			}
		}
	}
	
	@Override
	public boolean canUpdate() {
		return true;
	}
    
	@Override
	public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity pkt) {
		facing = ForgeDirection.getOrientation(pkt.func_148857_g().getByte("facing"));
	}
	
	@Override
	public Packet getDescriptionPacket() {
		NBTTagCompound tag = new NBTTagCompound();
		tag.setByte("facing", (byte) facing.ordinal());
		
        return new S35PacketUpdateTileEntity(xCoord, yCoord, zCoord, 0, tag);
	}
	
    /* IEnergyHandler */
    
    @Override
    public boolean canConnectEnergy(ForgeDirection from) {
        return from != facing;
    }

    @Override
    public int receiveEnergy(ForgeDirection from, int maxReceive, boolean simulate) {
        return from == facing ? 0 : storage.receiveEnergy(maxReceive, simulate);
    }

    @Override
    public int extractEnergy(ForgeDirection from, int maxExtract, boolean simulate) {
        return from == facing ? 0 : storage.extractEnergy(maxExtract, simulate);
    }

    @Override
    public int getEnergyStored(ForgeDirection from) {
        return from == facing ? 0 : storage.getEnergyStored();
    }

    @Override
    public int getMaxEnergyStored(ForgeDirection from) {
        return from == facing ? 0 : storage.getMaxEnergyStored();
    }
    
    public EnergyStorage getEnergyStorage(ForgeDirection from) {
        return from == facing ? null : storage;
    }
    
    /* IINVENTORY */
	@Override
	public int getSizeInventory() {
		return inventory.length;
	}

	@Override
	public ItemStack getStackInSlot(int slot) {
		return inventory[slot];
	}

	@Override
	public ItemStack decrStackSize(int i, int j) {
		ItemStack stack = getStackInSlot(i);

        if (stack != null)
            if (stack.stackSize <= j)
                setInventorySlotContents(i, null);
            else {
                stack = stack.splitStack(j);

                if (stack.stackSize == 0)
                    setInventorySlotContents(i, null);
            }

        return stack;
	}

	@Override
	public ItemStack getStackInSlotOnClosing(int slot) {
		return inventory[slot];
	}

	@Override
	public void setInventorySlotContents(int slot, ItemStack stack) {
		inventory[slot] = stack;
	}

	@Override
	public String getInventoryName() {
		return null;
	}

	@Override
	public boolean hasCustomInventoryName() {
		return false;
	}

	@Override
	public int getInventoryStackLimit() {
		return 64;
	}

	@Override
	public boolean isUseableByPlayer(EntityPlayer player) {
		return true;
	}

	@Override
	public void openInventory() {

	}

	@Override
	public void closeInventory() {

	}

	@Override
	public boolean isItemValidForSlot(int slot, ItemStack stack) {
		return false; // TODO
	}
}
