package enhanced.base.utilities;

import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.StatCollector;

public class Localisation {
    /**
     * Gets a localised string
     * @param mod Mod ID (prefixes the unlocalised string)
     * @param s Unlocalised string
     * @return Localised string
     */
    public static String get(String mod, String s) {
        return StatCollector.translateToLocal(mod + "." + s);
    }

    /**
     * @param mod Mod ID (prefixes the unlocalised string)
     * @param s Unlocalised string
     * @return [Error] Message
     */
    public static String getChatError(String mod, String s) {
        return String.format(StatCollector.translateToLocal(mod + ".error." + s), EnumChatFormatting.RED + StatCollector.translateToLocal(mod + ".error") + EnumChatFormatting.WHITE);
    }

    /**
     * @param mod Mod ID (prefixes the unlocalised string)
     * @param s Unlocalised string
     * @return [Success] Message
     */
    public static String getChatSuccess(String mod, String s) {
        return String.format(StatCollector.translateToLocal(mod + ".success." + s), EnumChatFormatting.GREEN + StatCollector.translateToLocal(mod + ".success") + EnumChatFormatting.WHITE);
    }

    /*** 
     * Checks to see if the localisation exists
     * @param mod Mod ID (Prefixes the unlocalised string)
     * @param s Unlocalised string
     * @return true if localisation can be found
     */
    public static boolean exists(String mod, String s) {
        return StatCollector.canTranslate(mod + "." + s);
    }
}
