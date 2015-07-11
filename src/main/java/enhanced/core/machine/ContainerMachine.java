package enhanced.core.machine;

import java.util.List;

import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.ICrafting;
import net.minecraftforge.common.util.ForgeDirection;
import enhanced.base.inventory.BaseContainer;
import enhanced.base.tile.TileMachineBase;

public abstract class ContainerMachine extends BaseContainer {
	protected TileMachineBase tile;
    protected int craftingProgress = -1;
    protected int power = -1;

	public ContainerMachine(TileMachineBase machine, InventoryPlayer p) {
		super(machine, p);
		tile = machine;
	}

	@Override
    public void detectAndSendChanges() {
    	super.detectAndSendChanges();
    	int cProgress = tile.processingTime;
    	int pwr = tile.getEnergyStored(ForgeDirection.UNKNOWN);
    	
    	for (int i = 0; i < crafters.size(); i++) {
            ICrafting icrafting = (ICrafting) crafters.get(i);

            if (craftingProgress != cProgress)
            	icrafting.sendProgressBarUpdate(this, 0, cProgress);
            if (power != pwr)
            	icrafting.sendProgressBarUpdate(this, 1, pwr);
        }
    	
    	detectChanges(crafters);
    	craftingProgress = cProgress;
    	power = pwr;
    }
    
    @Override
    public void updateProgressBar(int id, int status) {
    	if (id == 0) {
    		craftingProgress = status;
    		tile.processingTime = status;
    	}
    	else if (id == 1) {
    		power = status;
    		tile.getEnergyStorage(ForgeDirection.UNKNOWN).setEnergyStored(status);
    	}
    	else
    		updateClient(id, status);
    }
    
    protected abstract void detectChanges(List crafters);
    protected abstract void updateClient(int id, int status);
}
