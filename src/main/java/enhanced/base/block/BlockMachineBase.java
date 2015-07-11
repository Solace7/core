package enhanced.base.block;

import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import enhanced.base.tile.TileMachineBase;
import enhanced.core.Reference.ECMod;

public abstract class BlockMachineBase extends BlockContainerBase {
    protected static IIcon sideTexture;
    protected Object _MOD;

    public BlockMachineBase(String mod, Object modInstance, String name, Material material, CreativeTabs tab, float hard) {
        super(mod, name, material, tab, hard);
        _MOD = modInstance;
    }

    @Override
    public IIcon getIcon(IBlockAccess blockAccess, int x, int y, int z, int s) {
        TileEntity tile = blockAccess.getTileEntity(x, y, z);

        if (tile instanceof TileMachineBase) {
            TileMachineBase machine = (TileMachineBase) tile;

            if (s == machine.facing.ordinal())
                return super.getIcon(s, blockAccess.getBlockMetadata(x, y, z));
        }

        return sideTexture;
    }

    @Override
    public IIcon getIcon(int s, int m) {
        return s == 4 ? super.getIcon(s, m) : sideTexture;
    }

    @Override
    public void registerBlockIcons(IIconRegister register) {
        super.registerBlockIcons(register);
        sideTexture = register.registerIcon(ECMod.ID + ":machine_side");
    }

    @Override
    public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase entity, ItemStack stack) {
        TileEntity tile = world.getTileEntity(x, y, z);

        if (tile instanceof TileMachineBase) {
            TileMachineBase machine = (TileMachineBase) tile;
            int facing = MathHelper.floor_double(entity.rotationYaw * 4.0F / 360.0F + 0.5D) & 3;

            if (facing == 0)
                machine.facing = ForgeDirection.NORTH;
            else if (facing == 1)
                machine.facing = ForgeDirection.EAST;
            else if (facing == 2)
                machine.facing = ForgeDirection.SOUTH;
            else if (facing == 3)
                machine.facing = ForgeDirection.WEST;
        }
    }

	@Override
	public int damageDropped(int meta) {
		return meta;
	}

    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int p_149727_6_, float p_149727_7_, float p_149727_8_, float p_149727_9_) {
        player.openGui(_MOD, getGui(world.getBlockMetadata(x, y, z), player), world, x, y, z);
        return true;
    }

    protected abstract int getGui(int meta, EntityPlayer player);
}
