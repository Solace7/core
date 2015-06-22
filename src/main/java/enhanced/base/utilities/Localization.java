package enhanced.base.utilities;

import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.StatCollector;

public class Localization {
    public static String get(String mod, String s) {
        return StatCollector.translateToLocal(mod + "." + s);
    }

    public static String getChatError(String mod, String s) {
        return String.format(StatCollector.translateToLocal(mod + ".error." + s), EnumChatFormatting.RED + StatCollector.translateToLocal(mod + ".error") + EnumChatFormatting.WHITE);
    }

    public static String getChatSuccess(String mod, String s) {
        return String.format(StatCollector.translateToLocal(mod + ".success." + s), EnumChatFormatting.GREEN + StatCollector.translateToLocal(mod + ".success") + EnumChatFormatting.WHITE);
    }
}
