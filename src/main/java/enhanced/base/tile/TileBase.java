package enhanced.base.tile;

import io.netty.buffer.ByteBuf;
import net.minecraft.tileentity.TileEntity;
import enhanced.base.utilities.BlockPos;
import enhanced.base.utilities.WorldPos;

public abstract class TileBase extends TileEntity {
    public BlockPos getBlockPos() {
        return new BlockPos(xCoord, yCoord, zCoord);
    }

    public WorldPos getWorldPos() {
        return new WorldPos(xCoord, yCoord, zCoord, getWorldObj().provider.dimensionId);
    }
    
    @Override
    public boolean canUpdate() {
        return false;
    }

    public abstract void writeToGui(ByteBuf buffer);
    public abstract void readFromGui(ByteBuf buffer);
    
    /*@Override
    public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity pkt) {
    }
    
    @Override
    public Packet getDescriptionPacket() {
        NBTTagCompound tag = new NBTTagCompound();
        
        return new S35PacketUpdateTileEntity(xCoord, yCoord, zCoord, 0, tag);
    }*/
}
