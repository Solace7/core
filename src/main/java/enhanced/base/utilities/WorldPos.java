package enhanced.base.utilities;

import java.util.ArrayList;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.world.World;
import net.minecraftforge.common.DimensionManager;

public class WorldPos {
    int xPos, yPos, zPos;
    int dim;

    public WorldPos() {
        xPos = yPos = zPos = 0;
        dim = 0;
    }
    
    public WorldPos(int x, int y, int z, int d) {
        xPos = x;
        yPos = y;
        zPos = z;
        dim = d;
    }
    
    public WorldPos(BlockPos b, int d) {
        xPos = b.xPos;
        yPos = b.yPos;
        zPos = b.zPos;
        dim = d;
    }
    
    public WorldPos(WorldPos b) {
        xPos = b.xPos;
        yPos = b.yPos;
        zPos = b.zPos;
        dim = b.dim;
    }

    public int getDim() {
        return dim;
    }
    
    public boolean isCenterPoint() {
        return xPos == 0 && yPos == 0 && zPos == 0 && dim == 0;
    }
    
    public static WorldPos readFromNBT(NBTTagCompound nbt, String name) {
        if (nbt == null || !nbt.hasKey(name))
            return null;
        
        return readFromNBT(nbt.getCompoundTag(name));
    }
    
    public static WorldPos readFromNBT(NBTTagCompound nbt) {
        if (nbt == null || !nbt.hasKey("xPos") || !nbt.hasKey("yPos") || !nbt.hasKey("zPos") || !nbt.hasKey("dim"))
            return null;
        
        return new WorldPos(nbt.getInteger("xPos"), nbt.getInteger("yPos"), nbt.getInteger("zPos"), nbt.getInteger("dim"));
    }
    
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof WorldPos) {
            WorldPos w = (WorldPos) obj;
            return w.xPos == xPos && w.yPos == yPos && w.zPos == zPos && w.dim == dim;
        }
        
        return false;
    }
    
    public void writeToNBT(NBTTagCompound nbt) {
        nbt.setInteger("xPos", xPos);
        nbt.setInteger("yPos", yPos);
        nbt.setInteger("zPos", zPos);
        nbt.setInteger("dim", dim);
    }
    
    public void writeToNBT(NBTTagCompound nbt, String name) {
        NBTTagCompound t = new NBTTagCompound();
        writeToNBT(t);
        nbt.setTag(name, t);
    }
    
    @Override
    public int hashCode() {
        return xPos + zPos << 8 + yPos << 16 + dim << 24;
    }
    
    @Override
    public String toString() {
        return "(" + xPos + ", " + yPos + ", " + zPos + " @ " + dim + ")";
    }
    
    public boolean isWorldAvailable() {
        return getWorld() != null;
    }
    
    public World getWorld() {
        return DimensionManager.getWorld(dim);
    }
    
    public static void writeListToNBT(ArrayList<WorldPos> list, String name, NBTTagCompound tag) {
        if (list == null || list.isEmpty())
            return;
        
        NBTTagList tagList = new NBTTagList();
        
        for (WorldPos p : list) {
            NBTTagCompound t = new NBTTagCompound();
            p.writeToNBT(t);
            tagList.appendTag(t);
        }
        
        tag.setTag(name, tagList);
    }
    
    public static ArrayList readListFromNBT(NBTTagCompound tag, String name) {
        ArrayList<WorldPos> list = new ArrayList<WorldPos>();
        
        if (tag.hasKey(name)) {
            NBTTagList tagList = tag.getTagList(name, 10);
            
            for (int i = 0; i < tagList.tagCount(); i++) {
                NBTTagCompound t = tagList.getCompoundTagAt(i);
                WorldPos p = WorldPos.readFromNBT(t);
                list.add(p);
            }
        }
        
        return list;
    }
}
