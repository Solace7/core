package enhanced.base.xmod;

import java.lang.reflect.Method;

import cpw.mods.fml.common.Loader;
import enhanced.core.EnhancedCore;

public class ComputerCraft {
    public static final String MOD_ID = "ComputerCraft";
    static boolean failed = false;

    @SuppressWarnings({ "rawtypes", "unchecked" })
    public static void registerPeripheralProvider(Object o) {
        if (!Loader.isModLoaded(MOD_ID) || failed)
            return;

        try {
            Class computerCraft = Class.forName("dan200.computercraft.ComputerCraft");
            Method computerCraft_registerPeripheralProvider = computerCraft.getMethod("registerPeripheralProvider", new Class[] { Class.forName("dan200.computercraft.api.peripheral.IPeripheralProvider") });
            computerCraft_registerPeripheralProvider.invoke(null, o);
        } catch (Exception e) {
            EnhancedCore.instance.getLogger().error("Could not load the ComputerCraft API. ComputerCraft functionality has been disabled.");
            failed = true;
        }
    }
}
