package enhanced.core.item;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import buildcraft.api.tools.IToolWrench;
import cofh.api.block.IDismantleable;
import enhanced.base.item.ItemBase;
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
