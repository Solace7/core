package enhanced.core.machine.glassfab;

import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.util.ForgeDirection;
import enhanced.base.client.gui.BaseGui;
import enhanced.base.client.gui.elements.ElementFluid;
import enhanced.base.client.gui.elements.ElementRedstoneFlux;
import enhanced.base.utilities.Localisation;
import enhanced.core.Reference.ECMod;

public class GuiGlassFabricator extends BaseGui {
    ElementRedstoneFlux flux;
    ElementFluid fluid;
    TileGlassFabricator glass;
    
    public GuiGlassFabricator(TileGlassFabricator glass, InventoryPlayer p) {
        super(new ContainerGlassFabricator(glass, p));
        texture = new ResourceLocation(ECMod.ID + ":textures/gui/glass_fabricator.png");
        this.glass = glass;
        setCombinedInventory();
        name = Localisation.get(ECMod.ID, "gui.glassFabricator");
    }
    
    @Override
    public void initGui() {
        super.initGui();
        flux = new ElementRedstoneFlux(this, getSizeX() - 21, 22, glass.getEnergyStorage(ForgeDirection.UNKNOWN));
        fluid = new ElementFluid(this, 7, 15, glass.getTank(ForgeDirection.UNKNOWN));
        addElement(flux);
        addElement(fluid);
    }
    
    @Override
    protected void drawBackgroundTexture() {
    	super.drawBackgroundTexture();
    	int num = (int) (22 * (glass.processingTime / glass.PROCESS_TIME));
    	if (glass.processingTime > 0)
    	num = 22 - num;
        drawTexturedModalRect(guiLeft + 73, guiTop + 35, 0, 239, num, 15);
    }
}
