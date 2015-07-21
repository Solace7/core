package enhanced.base.utilities;

import java.util.ArrayList;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.world.ChunkCoordIntPair;
import net.minecraftforge.common.util.ForgeDirection;

public class BlockPos {
    int xPos, yPos, zPos;
    
    public BlockPos() {
        xPos = yPos = zPos = 0;
    }
    
    public BlockPos(int x, int y, int z) {
        xPos = x;
        yPos = y;
        zPos = z;
    }
    
    public BlockPos(BlockPos b) {
        xPos = b.xPos;
        yPos = b.yPos;
        zPos = b.zPos;
    }

    public int getX() {
        return xPos;
    }
    
    public int getY() {
        return yPos;
    }
    
    public int getZ() {
        return zPos;
    }
    
    public boolean isCenterPoint() {
        return xPos == 0 && yPos == 0 && zPos == 0;
    }
    
    public ChunkCoordIntPair getChunk() {
        return new ChunkCoordIntPair(xPos >> 4, zPos >> 4);
    }
    
    public void writeToNBT(NBTTagCompound nbt, String name) {
        NBTTagCompound t = new NBTTagCompound();
        writeToNBT(t);
        nbt.setTag(name, t);
    }
    
    public void writeToNBT(NBTTagCompound nbt) {
        nbt.setInteger("xPos", xPos);
        nbt.setInteger("yPos", yPos);
        nbt.setInteger("zPos", zPos);
    }
    
    public static BlockPos readFromNBT(NBTTagCompound nbt, String name) {
        if (nbt == null || !nbt.hasKey(name))
            return null;
        
        return readFromNBT(nbt.getCompoundTag(name));
    }
    
    public static BlockPos readFromNBT(NBTTagCompound nbt) {
        if (nbt == null || !nbt.hasKey("xPos") || !nbt.hasKey("yPos") || !nbt.hasKey("zPos"))
            return null;
        
        return new BlockPos(nbt.getInteger("xPos"), nbt.getInteger("yPos"), nbt.getInteger("zPos"));
    }
    
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof BlockPos) {
            BlockPos b = (BlockPos) obj;
            return b.xPos == xPos && b.yPos == yPos && b.zPos == zPos;
        }
        
        return false;
    }
    
    @Override
    public int hashCode() {
        return xPos + zPos << 8 + yPos << 16;
    }
    
    @Override
    public String toString() {
        return "(" + xPos + ", " + yPos + ", " + zPos + ")";
    }
    
    public BlockPos offset(int x, int y, int z) {
        return new BlockPos(xPos + x, yPos + y, zPos + z);
    }
    
    public BlockPos offset(ForgeDirection d) {
        return offset(d.offsetX, d.offsetY, d.offsetZ);
    }
    
    public static BlockPos offset(BlockPos p, int x, int y, int z) {
        return new BlockPos(p.xPos + x, p.yPos + y, p.zPos + z);
    }
    
    public static BlockPos offset(BlockPos p, ForgeDirection d) {
        return offset(p, d.offsetX, d.offsetY, d.offsetZ);
    }
    
    public static void writeListToNBT(ArrayList<BlockPos> list, String name, NBTTagCompound tag) {
        if (list == null || list.isEmpty())
            return;
        
        NBTTagList tagList = new NBTTagList();
        
        for (BlockPos p : list) {
            NBTTagCompound t = new NBTTagCompound();
            p.writeToNBT(t);
            tagList.appendTag(t);
        }
        
        tag.setTag(name, tagList);
    }
    
    public static ArrayList readListFromNBT(NBTTagCompound tag, String name) {
        ArrayList<BlockPos> list = new ArrayList<BlockPos>();
        
        if (tag.hasKey(name)) {
            NBTTagList tagList = tag.getTagList(name, 10);
            
            for (int i = 0; i < tagList.tagCount(); i++) {
                list.add(BlockPos.readFromNBT(tagList.getCompoundTagAt(i)));
            }
        }
        
        return list;
    }
}
