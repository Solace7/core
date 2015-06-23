package enhanced.core;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import enhanced.base.mod.BaseMod;
import enhanced.core.network.ProxyCommon;

@Mod(name = EnhancedCore.MOD_NAME, modid = EnhancedCore.MOD_ID, version = EnhancedCore.MOD_VERSION, dependencies = EnhancedCore.MOD_DEPENDENCIES)
public class EnhancedCore extends BaseMod {
    public static final String MOD_ID = "enhancedcore", MOD_ID_SHORT = "ecore", MOD_NAME = "Enhanced Core", MOD_URL = "https://raw.githubusercontent.com/enhancedportals/VERSION/master/VERSION%20-%20Enhanced%20Core", MOD_VERSION = "1.0", MOD_DEPENDENCIES = "after:ThermalExpansion";

    @Instance(MOD_ID)
    public static EnhancedCore instance;

    @SidedProxy(clientSide = "enhanced.core.network.ProxyClient", serverSide = "enhanced.core.network.ProxyCommon")
    public static ProxyCommon proxy;

    public EnhancedCore() {
        super(MOD_URL, MOD_ID, MOD_ID_SHORT, MOD_NAME, MOD_VERSION);
    }

    // Startup

    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        super.preInit(event, proxy);
    }

    @EventHandler
    @Override
    public void init(FMLInitializationEvent event) {
        super.init(event);
    }

    @EventHandler
    @Override
    public void postInit(FMLPostInitializationEvent event) {
        super.postInit(event);
    }
}
