package enhanced.core.network;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import cpw.mods.fml.common.network.IGuiHandler;
import enhanced.core.Reference.ECGui;
import enhanced.core.machine.glassfab.ContainerGlassFabricator;
import enhanced.core.machine.glassfab.GuiGlassFabricator;
import enhanced.core.machine.glassfab.TileGlassFabricator;

public class GuiHandler implements IGuiHandler {
    @Override
    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        ECGui gui = ECGui.get(ID);

        if (gui != null) {
            if (gui == ECGui.glassFabricator) {
                return new ContainerGlassFabricator((TileGlassFabricator) world.getTileEntity(x, y, z), player.inventory);
            }
        }

        return null;
    }

    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        ECGui gui = ECGui.get(ID);

        if (gui != null) {
            if (gui == ECGui.glassFabricator) {
                return new GuiGlassFabricator((TileGlassFabricator) world.getTileEntity(x, y, z), player.inventory);
            }
        }
        return null;
    }
}
