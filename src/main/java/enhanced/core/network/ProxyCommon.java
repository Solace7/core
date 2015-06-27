package enhanced.core.network;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

import net.minecraft.potion.Potion;
import enhanced.base.mod.BaseProxy;
import enhanced.base.network.packet.PacketGui;
import enhanced.base.network.packet.PacketGuiData;
import enhanced.core.EnhancedCore;
import enhanced.core.Reference.ECItems;
import enhanced.core.item.PotionFeatherfall;

public class ProxyCommon extends BaseProxy {
    @Override
    public void registerBlocks() {

    }

    @Override
    public void registerItems() {

    }

    @Override
    public void registerTileEntities() {

    }

    @Override
    public void registerRecipes() {

    }

    @Override
    public void registerConfiguration() {
        EnhancedCore.instance.CHECK_FOR_UPDATES = config.get("General", "UpdateCheck", true, "Allow checking for updates from " + EnhancedCore.MOD_URL).getBoolean();
    }

    @Override
    public void registerPackets() {
        EnhancedCore.instance.packetPipeline.registerPacket(PacketGuiData.class);
        EnhancedCore.instance.packetPipeline.registerPacket(PacketGui.class);
    }
    
    @Override
    public void registerPotions() {
        Potion[] potionTypes = null;

        for (Field f : Potion.class.getDeclaredFields()) {
            f.setAccessible(true);
            try {
                if (f.getName().equals("potionTypes") || f.getName().equals("field_76425_a")) {
                    Field modfield = Field.class.getDeclaredField("modifiers");
                    modfield.setAccessible(true);
                    modfield.setInt(f, f.getModifiers() & ~Modifier.FINAL);

                    potionTypes = (Potion[]) f.get(null);
                    final Potion[] newPotionTypes = new Potion[256];
                    System.arraycopy(potionTypes, 0, newPotionTypes, 0, potionTypes.length);
                    f.set(null, newPotionTypes);
                }
            } catch (Exception e) {
                System.err.println("Severe error, please report this to the mod author:");
                System.err.println(e);
            }
        }

        ECItems.potionFeatherfall = new PotionFeatherfall(40, false, 0);
    }
}
