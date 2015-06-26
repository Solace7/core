package enhanced.base.mod;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.net.URL;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.relauncher.Side;
import enhanced.base.network.LogOnHandler;
import enhanced.base.network.PacketPipeline;
import enhanced.base.utilities.Localisation;

public abstract class BaseMod {
    protected File _CONFIG_BASE;
    protected Logger _LOGGER;
    protected String _MOD_URL, _MOD_ID, _MOD_NAME, _MOD_VER;
    protected BaseProxy _PROXY;
    protected String UPDATE_LATEST_VER = null;

    public boolean CHECK_FOR_UPDATES = true;
    public CreativeTab creativeTab;
    public PacketPipeline packetPipeline;

    public BaseMod(String url, String id, String short_id, String name, String ver) {
        _MOD_URL = url;
        _MOD_ID = id;
        _MOD_NAME = name;
        _MOD_VER = ver;
        _LOGGER = LogManager.getLogger(_MOD_NAME);
        packetPipeline = new PacketPipeline(short_id);
    }

    public Logger getLogger() {
        return _LOGGER;
    }

    protected void checkForUpdates() {
        try {
            URL versionIn = new URL(_MOD_URL);
            BufferedReader in = new BufferedReader(new InputStreamReader(versionIn.openStream()));
            UPDATE_LATEST_VER = in.readLine();

            if (FMLCommonHandler.instance().getSide() == Side.SERVER && !UPDATE_LATEST_VER.equals(_MOD_VER))
                getLogger().info(String.format(Localisation.get(_MOD_ID, "chat.updateMessage"), UPDATE_LATEST_VER, _MOD_VER));
            else
                FMLCommonHandler.instance().bus().register(new LogOnHandler(UPDATE_LATEST_VER, _MOD_VER, _MOD_ID));
        } catch (Exception e) {
            getLogger().warn("Unable to get the latest version information");
            UPDATE_LATEST_VER = _MOD_VER;
        }
    }

    public void init(FMLInitializationEvent event) {
        _PROXY.init();
    }

    public void preInit(FMLPreInitializationEvent event, BaseProxy proxy) {
        _PROXY = proxy;
        _CONFIG_BASE = event.getSuggestedConfigurationFile().getParentFile();
        creativeTab = new CreativeTab(_MOD_ID);
        _PROXY.preInit(new File(_CONFIG_BASE, _MOD_NAME + ".cfg"));
        packetPipeline.initialize();
        _PROXY.registerBlocks();
        _PROXY.registerTileEntities();
        _PROXY.registerItems();
        _PROXY.registerPackets();
        _PROXY.registerPotions();
    }

    public void postInit(FMLPostInitializationEvent event) {
        _PROXY.postInit();
        _PROXY.registerRecipes();
        packetPipeline.postInitialize();

        if (CHECK_FOR_UPDATES)
            checkForUpdates();
    }
}
