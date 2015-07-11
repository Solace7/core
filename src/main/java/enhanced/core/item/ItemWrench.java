package enhanced.core.item;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.IFluidHandler;
import buildcraft.api.tools.IToolWrench;
import cofh.api.block.IDismantleable;
import enhanced.base.item.ItemBase;
import enhanced.base.tile.TileMachineBase;
import enhanced.core.EnhancedCore;
import enhanced.core.Reference.ECMod;

public class ItemWrench extends ItemBase implements IToolWrench {
	public ItemWrench(String n) {
		super(ECMod.ID, n, EnhancedCore.instance.creativeTab);
		setMaxStackSize(1);
	}

	@Override
	public boolean canWrench(EntityPlayer player, int x, int y, int z) {
		return true;
	}

	@Override
	public boolean doesSneakBypassUse(World world, int x, int y, int z, EntityPlayer player) {
		return true;
	}

	@Override
	public void wrenchUsed(EntityPlayer player, int x, int y, int z) {

	}

	@Override
	public boolean onItemUseFirst(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ) {
		if (world.isRemote)
			return false;
		
		TileEntity t = world.getTileEntity(x, y, z);

		if (player.isSneaking()) {
			if (t instanceof TileMachineBase) {
				((TileMachineBase) t).getEnergyStorage(ForgeDirection.UNKNOWN).setEnergyStored(35000);
				return !world.isRemote;
			}
		} else {
			if (t instanceof IFluidHandler) {
				((IFluidHandler) t).fill(ForgeDirection.UP, new FluidStack(FluidRegistry.WATER, 1000), true);
				return !world.isRemote;
			}
		}

		if (player.isSneaking()) {
			Block b = world.getBlock(x, y, z);

			if (b instanceof IDismantleable) {
				IDismantleable dis = (IDismantleable) b;

				if (dis.canDismantle(player, world, x, y, z)) {
					dis.dismantleBlock(player, world, x, y, z, false);
					return !world.isRemote;
				}
			}
		}

		return super.onItemUseFirst(stack, player, world, x, y, z, side, hitX, hitY, hitZ);
	}
}
