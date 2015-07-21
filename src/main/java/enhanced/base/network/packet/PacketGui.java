package enhanced.base.network.packet;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import enhanced.base.tile.TileBase;

public class PacketGui extends PacketBase {
    TileBase tile;
    int x, y, z;
    ByteBuf buf;

    public PacketGui() {

    }

    public PacketGui(TileBase t) {
        tile = t;
    }

    @Override
    public void decodeInto(ChannelHandlerContext ctx, ByteBuf buffer) {
        x = buffer.readInt();
        y = buffer.readInt();
        z = buffer.readInt();
        buf = buffer;
    }

    @Override
    public void encodeInto(ChannelHandlerContext ctx, ByteBuf buffer) {
        buffer.writeInt(tile.xCoord);
        buffer.writeInt(tile.yCoord);
        buffer.writeInt(tile.zCoord);
        tile.writeToGui(buffer);
    }

    @Override
    public void handleClientSide(EntityPlayer player) {
        TileEntity t = player.worldObj.getTileEntity(x, y, z);

        if (t != null && t instanceof TileBase) {
            tile = (TileBase) t;
            tile.readFromGui(buf);
        }
    }

    @Override
    public void handleServerSide(EntityPlayer player) {

    }
}
