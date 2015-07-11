package enhanced.core.machine.glassfab;

import java.util.HashMap;
import java.util.Map.Entry;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidContainerRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTank;
import net.minecraftforge.fluids.FluidTankInfo;
import net.minecraftforge.fluids.IFluidHandler;
import net.minecraftforge.oredict.OreDictionary;
import enhanced.base.tile.TileMachineBase;
import enhanced.base.utilities.ComparableItemStack;
import enhanced.core.Reference.ECItems;
import enhanced.core.machine.RecipeMachine;

public class TileGlassFabricator extends TileMachineBase implements IFluidHandler {
	public static HashMap<RecipeMachine, ItemStack> recipes = new HashMap<RecipeMachine, ItemStack>();
	FluidTank tank;
	
	public TileGlassFabricator() {
		super(35000);
		tank = new FluidTank(FluidContainerRegistry.BUCKET_VOLUME * 10);
		inventory = new ItemStack[3];
	}

	@Override
	protected Entry<RecipeMachine, ItemStack> getRecipe() {
		ItemStack a = inventory[0], b = inventory[1];
		
		if (a == null && tank.getFluid() == null)
			return null;
		
		for (Entry<RecipeMachine, ItemStack> recipe : recipes.entrySet()) {
			RecipeGlassFabricator r = (RecipeGlassFabricator) recipe.getKey();

			if (r.isFirstItem(a) && r.isSecondItem(b)) {
				FluidStack fluid = tank.getFluid();
				boolean fluidMatches = false;
				
				if (fluid != null && r.fluid != null) {
					fluidMatches = fluid.isFluidEqual(r.fluid);
				} else if (fluid != null && r.fluid == null)
					fluidMatches = true;
				else if (fluid == null && r.fluid == null)
					fluidMatches = true;
				
				return fluidMatches ? recipe : null;
			}
		}

		return null;
	}
	
	@Override
	protected boolean hasEnoughComponents(RecipeMachine machine) {
		RecipeGlassFabricator recipe = (RecipeGlassFabricator) machine;
		
		if (recipe.input1 != null) { // Do we have enough items in slot one?
			if (inventory[0].stackSize < recipe.input1.getStackSize())
				return false;
		}
		
		if (recipe.input2 != null) { // Do we have enough items in slot two?
			if (inventory[1].stackSize < recipe.input2.getStackSize())
				return false;
		}
		
		if (recipe.fluid != null) { // Do we have enough fluid in the fluid tank?
			FluidStack f = tank.getFluid();
			
			if (f == null || f.amount < recipe.fluid.amount)
				return false;
		}
		
		return true;
	}
	
	@Override
	protected boolean hasEnoughExit(Entry<RecipeMachine, ItemStack> recipe) {
		if (inventory[2] != null) {
			if (!new ComparableItemStack(recipe.getValue()).matchesItem(inventory[2])) // Same item?
				return false;
			
			if (inventory[2].stackSize + recipe.getValue().stackSize > inventory[2].getMaxStackSize()) // Can we add to the stack?
				return false;
		}
		
		return true;
	}
	
	@Override
	protected void handleRecipeCompleted(Entry<RecipeMachine, ItemStack> recipe) {
		RecipeGlassFabricator r = (RecipeGlassFabricator) recipe.getKey();
		ItemStack result = recipe.getValue().copy();
		
		if (r.input1 != null)
			decrStackSize(0, r.input1.getStackSize());
		
		if (r.input2 != null && !r.input2.matchesItem(new ItemStack(ECItems.mould, 1, OreDictionary.WILDCARD_VALUE)))
			decrStackSize(1, r.input2.getStackSize());
		
		if (r.fluid != null)
			tank.drain(r.fluid.amount, true);

		if (inventory[2] != null)
			inventory[2].stackSize += result.stackSize;
		else
			setInventorySlotContents(2, result);

		resetCrafting();
	}

	@Override
	protected void saveData(NBTTagCompound nbt) {
		tank.writeToNBT(nbt);
	}

	@Override
	protected void loadData(NBTTagCompound nbt) {
		tank.readFromNBT(nbt);
	}
	
	/* IFluidHandler */

	@Override
	public int fill(ForgeDirection from, FluidStack resource, boolean doFill) {
		return from == facing ? 0 : tank.fill(resource, doFill);
	}

	@Override
	public FluidStack drain(ForgeDirection from, FluidStack resource, boolean doDrain) {
		return from == facing ? null : tank.drain(resource.amount, doDrain);
	}

	@Override
	public FluidStack drain(ForgeDirection from, int maxDrain, boolean doDrain) {
		return from == facing ? null : tank.drain(maxDrain, doDrain);
	}

	@Override
	public boolean canFill(ForgeDirection from, Fluid fluid) {
		return from == facing ? false : true;
	}

	@Override
	public boolean canDrain(ForgeDirection from, Fluid fluid) {
		return from == facing ? false : true;
	}

	@Override
	public FluidTankInfo[] getTankInfo(ForgeDirection from) {
		return from == facing ? new FluidTankInfo[] { } : new FluidTankInfo[] { tank.getInfo() };
	}

	public FluidTank getTank(ForgeDirection from) {
		return from == facing ? null : tank;
	}
}
