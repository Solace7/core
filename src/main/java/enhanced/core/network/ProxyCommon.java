package enhanced.core.network;

import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.registry.GameRegistry;
import enhanced.base.mod.BaseProxy;
import enhanced.base.network.packet.PacketGui;
import enhanced.base.network.packet.PacketGuiData;
import enhanced.core.EnhancedCore;
import enhanced.core.Reference.ECBlocks;
import enhanced.core.Reference.ECConfiguration;
import enhanced.core.Reference.ECItems;
import enhanced.core.Reference.ECMod;
import enhanced.core.item.PotionFeatherfall;
import enhanced.core.machine.ItemMachine;
import enhanced.core.machine.customizer.TileCustomizer;
import enhanced.core.machine.glassfab.RecipeGlassFabricator;
import enhanced.core.machine.glassfab.TileGlassFabricator;

public class ProxyCommon extends BaseProxy {
    @Override
    public void preInit(File file) {
        super.preInit(file);
        
        if (Loader.isModLoaded("enhancedportals") && !ECConfiguration.techMode) {
            ECConfiguration.techMode = true;
        }
    }
    
    @Override
    public void registerBlocks() {
        GameRegistry.registerBlock(ECBlocks.machines, ItemMachine.class, "machine");
    }

    @Override
    public void registerItems() {
        GameRegistry.registerItem(ECItems.wrench, "wrench");
        GameRegistry.registerItem(ECItems.mould, "mould");
    }

    @Override
    public void registerTileEntities() {
        GameRegistry.registerTileEntity(TileGlassFabricator.class, "ecGFab");
        GameRegistry.registerTileEntity(TileCustomizer.class, "ecCust");
    }

    @Override
    public void registerRecipes() {
    	TileGlassFabricator.recipes.put(new RecipeGlassFabricator(new ItemStack(Blocks.sand), new ItemStack(ECItems.mould, 1, 0), null, 1000), new ItemStack(Blocks.glass, 1));
    	TileGlassFabricator.recipes.put(new RecipeGlassFabricator(new ItemStack(Blocks.sand), new ItemStack(ECItems.mould, 1, 1), null, 1000), new ItemStack(Blocks.glass_pane, 2));
    	TileGlassFabricator.recipes.put(new RecipeGlassFabricator(null, new ItemStack(ECItems.mould, 1, 0), new FluidStack(FluidRegistry.WATER, 1000), 1000), new ItemStack(Blocks.glass, 1));
    }

    @Override
    public void registerConfiguration() {
        EnhancedCore.instance.CHECK_FOR_UPDATES = config.get("General", "UpdateCheck", true, "Allow checking for updates from " + ECMod.url).getBoolean();
        ECConfiguration.techMode = config.get("General", "TechMode", ECConfiguration.techMode, "Enable all tech features from Enhanced Core? Enhanced Portals will force this to be on.").getBoolean();
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

        ECItems.potionFeatherfall = new PotionFeatherfall(ECConfiguration.potionFeatherfall, false, 0);
    }
}
