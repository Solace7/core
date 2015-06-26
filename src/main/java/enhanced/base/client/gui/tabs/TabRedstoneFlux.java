package enhanced.base.client.gui.tabs;

import net.minecraftforge.common.util.ForgeDirection;
import cofh.api.energy.IEnergyHandler;
import enhanced.base.client.gui.BaseGui;
import enhanced.base.utilities.Localisation;
import enhanced.core.EnhancedCore;

public class TabRedstoneFlux extends BaseTab {
    IEnergyHandler handler;
    int powerCostPerTick = 0;

    public TabRedstoneFlux(BaseGui gui, IEnergyHandler h) {
        super(gui);
        name = Localisation.get(EnhancedCore.MOD_ID, "tab.redstoneFlux");
        handler = h;
        maxHeight = 110;
        titleColour = 0xDDDD00;
        backgroundColor = 0xDD6600;
    }

    @Override
    public void drawFullyOpened() {
        parent.getFontRenderer().drawStringWithShadow(Localisation.get(EnhancedCore.MOD_ID, "tab.redstoneFlux.maxPower"), posX + 10, posY + 20, 0xAAAAAA);
        parent.getFontRenderer().drawString(handler.getMaxEnergyStored(ForgeDirection.UNKNOWN) + " RF", posX + 17, posY + 32, 0x000000);

        parent.getFontRenderer().drawStringWithShadow(Localisation.get(EnhancedCore.MOD_ID, "tab.redstoneFlux.storedPower"), posX + 10, posY + 45, 0xAAAAAA);
        parent.getFontRenderer().drawString(handler.getEnergyStored(ForgeDirection.UNKNOWN) + " RF", posX + 17, posY + 57, 0x000000);

        parent.getFontRenderer().drawStringWithShadow(Localisation.get(EnhancedCore.MOD_ID, "tab.redstoneFlux.powerUsage"), posX + 10, posY + 70, 0xAAAAAA);
        parent.getFontRenderer().drawString(powerCostPerTick * 20 + " RF/s", posX + 17, posY + 83, 0x000000);
        parent.getFontRenderer().drawString(powerCostPerTick + " RF/t", posX + 17, posY + 94, 0x000000);
    }

    @Override
    public void drawFullyClosed() {

    }

    /**
     * Sets the power cost per tick
     *
     * @param power
     *            Power cost per tick
     */
    public void setPowerCost(int power) {
        powerCostPerTick = power;
    }
}
