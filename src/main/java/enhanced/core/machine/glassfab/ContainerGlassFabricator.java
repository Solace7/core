package enhanced.core.machine.glassfab;

import java.util.List;

import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.Slot;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import enhanced.core.machine.ContainerMachine;

public class ContainerGlassFabricator extends ContainerMachine {
	int fluid = -1;
	int fluidAmt = -1;

	public ContainerGlassFabricator(TileGlassFabricator glass, InventoryPlayer p) {
		super(glass, p);
		addSlotToContainer(new Slot(glass, 0, 49, 25));
		addSlotToContainer(new Slot(glass, 1, 49, 45));
		addSlotToContainer(new Slot(glass, 2, 107, 35));
	}
	
	@Override
	protected void detectChanges(List crafters) {
		FluidStack Fluid = ((TileGlassFabricator) tile).getTank(ForgeDirection.UNKNOWN).getFluid();
		int _fluid = Fluid == null ? -1 : Fluid.getFluidID();
		int _fluidAmt = _fluid == -1 ? 0 : Fluid.amount;

		for (int i = 0; i < crafters.size(); i++) {
			ICrafting icrafting = (ICrafting) crafters.get(i);
			
			if (fluid != _fluid)
				icrafting.sendProgressBarUpdate(this, 2, _fluid);
			if (fluidAmt != _fluidAmt)
				icrafting.sendProgressBarUpdate(this, 3, _fluidAmt);
		}
		
		fluid = _fluid;
		fluidAmt = _fluidAmt;
	}

	@Override
	protected void updateClient(int id, int status) {
		TileGlassFabricator glass = (TileGlassFabricator) tile;

		if (id == 2) {
			if (status == -1)
				glass.getTank(ForgeDirection.UNKNOWN).setFluid(null);
			else
				glass.getTank(ForgeDirection.UNKNOWN).setFluid(new FluidStack(FluidRegistry.getFluid(status), 0));
		} else if (id == 3) {
			FluidStack _fluid = glass.getTank(ForgeDirection.UNKNOWN).getFluid();

			if (_fluid != null)
				glass.getTank(ForgeDirection.UNKNOWN).setFluid(new FluidStack(_fluid.getFluid(), status));
		}
	}
}
