package enhanced.base.utilities;

import net.minecraft.block.Block;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class WorldUtilities {
    public static boolean isAirBlock(World world, BlockPos pos) {
        return world.isAirBlock(pos.getX(), pos.getY(), pos.getZ());
    }

    public static boolean isAirBlock(WorldPos pos) {
        return isAirBlock(pos.getWorld(), pos);
    }

    public static Block getBlock(World world, BlockPos pos) {
        return world.getBlock(pos.getX(), pos.getY(), pos.getZ());
    }

    public static Block getBlock(WorldPos pos) {
        return getBlock(pos.getWorld(), pos);
    }

    public static TileEntity getTileEntity(World world, BlockPos pos) {
        return world.getTileEntity(pos.getX(), pos.getY(), pos.getZ());
    }

    public static TileEntity getTileEntity(WorldPos pos) {
        return getTileEntity(pos.getWorld(), pos);
    }

    public static void setBlock(World world, BlockPos pos, Block block, int meta, int state) {
        world.setBlock(pos.getX(), pos.getY(), pos.getZ(), block, meta, state);
    }

    public static void setBlockToAir(World world, BlockPos pos) {
        world.setBlockToAir(pos.getX(), pos.getY(), pos.getZ());
    }

    public static void markBlockForUpdate(World world, BlockPos pos) {
        world.markBlockForUpdate(pos.getX(), pos.getY(), pos.getZ());
    }

    public static void setBlock(WorldPos pos, Block block, int meta, int state) {
        setBlock(pos.getWorld(), pos, block, meta, state);
    }

    public static void setBlockToAir(WorldPos pos) {
        setBlockToAir(pos.getWorld(), pos);
    }
}
