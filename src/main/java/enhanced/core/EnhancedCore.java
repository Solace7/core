package enhanced.core;

import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.network.NetworkRegistry;
import enhanced.base.mod.BaseMod;
import enhanced.core.Reference.ECItems;
import enhanced.core.Reference.ECMod;
import enhanced.core.network.GuiHandler;
import enhanced.core.network.ProxyCommon;

@Mod(name = ECMod.name, modid = ECMod.ID, version = ECMod.version, dependencies = ECMod.dependencies)
public class EnhancedCore extends BaseMod {
    @Instance(ECMod.ID)
    public static EnhancedCore instance;

    @SidedProxy(clientSide = "enhanced.core.network.ProxyClient", serverSide = "enhanced.core.network.ProxyCommon")
    public static ProxyCommon proxy;

    public EnhancedCore() {
        super(ECMod.url, ECMod.ID, ECMod.shortID, ECMod.name, ECMod.version);
        MinecraftForge.EVENT_BUS.register(this);
    }

    // Startup

    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        super.preInit(event, proxy);
        creativeTab.setItem(new ItemStack(ECItems.wrench));
    }

    @EventHandler
    @Override
    public void init(FMLInitializationEvent event) {
        super.init(event);
        NetworkRegistry.INSTANCE.registerGuiHandler(this, new GuiHandler());
    }

    @EventHandler
    @Override
    public void postInit(FMLPostInitializationEvent event) {
        super.postInit(event);
    }
    
    // Subscribed events
    
    @SubscribeEvent
    public void onEntityUpdate(LivingUpdateEvent event) {
        PotionEffect effect = event.entityLiving.getActivePotionEffect(ECItems.potionFeatherfall);

        if (effect != null) {
            event.entityLiving.fallDistance = 0f;

            if (event.entityLiving.getActivePotionEffect(ECItems.potionFeatherfall).getDuration() <= 0)
                event.entityLiving.removePotionEffect(ECItems.potionFeatherfall.id);
        }
    }
}
