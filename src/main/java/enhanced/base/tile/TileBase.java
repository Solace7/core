package enhanced.base.tile;

import io.netty.buffer.ByteBuf;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ChunkCoordinates;
import enhanced.base.utilities.DimensionCoordinates;

public class TileBase extends TileEntity {
    public ChunkCoordinates getChunkCoordinates() {
        return new ChunkCoordinates(xCoord, yCoord, zCoord);
    }

    public DimensionCoordinates getDimensionCoordinates() {
        return new DimensionCoordinates(xCoord, yCoord, zCoord, getWorldObj().provider.dimensionId);
    }
    
    @Override
    public boolean canUpdate() {
        return false;
    }

    public void packetGuiFill(ByteBuf buffer) {

    }

    public void packetGuiUse(ByteBuf buffer) {

    }
}
