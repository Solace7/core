package enhanced.core;

import enhanced.core.item.ItemMould;
import enhanced.core.item.ItemWrench;
import enhanced.core.item.PotionFeatherfall;
import enhanced.core.machine.BlockMachine;

public class Reference {
    public static class ECMod {
        public static final String name = "Enhanced Core";
        public static final String ID = "enhancedcore";
        public static final String shortID = "ecore";
        public static final String version = "1.0.0";
        public static final String dependencies = "after:ThermalExpansion";
        public static final String url = "https://raw.githubusercontent.com/enhancedportals/VERSION/master/VERSION%20-%20Enhanced%20Core";
        public static final String proxyClient = "enhanced.portals.network.ProxyClient";
        public static final String proxyCommon = "enhanced.portals.network.ProxyCommon";
    }
    
    public static class ECBlocks { 
        public static final BlockMachine machines = new BlockMachine("machine");
    }
    
    public static class ECItems {
        public static PotionFeatherfall potionFeatherfall;
        public static final ItemWrench wrench = new ItemWrench("wrench");
        public static final ItemMould mould = new ItemMould("mould");
    }
    
    public static class ECConfiguration { 
        public static int potionFeatherfall = 40;
        public static boolean techMode = true;
    }
    
    public static enum ECGui {
        glassFabricator,
        customizer;
        
        public static ECGui[] VALUES = values();

        public static ECGui get(int iD) {
            if (iD < 0 || iD > VALUES.length)
                return null;
            
            return VALUES[iD];
        }
    }
}
