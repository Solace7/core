package enhanced.base.network;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ChatComponentText;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent;
import enhanced.base.utilities.Localization;

public class LogOnHandler {
    boolean displayed;
    String LATEST = null, RUNNING = null, MOD = null;

    public LogOnHandler(String latest, String running, String mod) {
        MOD = mod;
        LATEST = latest;
        RUNNING = running;
    }

    @SubscribeEvent
    public void onLogIn(PlayerEvent.PlayerLoggedInEvent login) {
        if (!displayed && login.player != null && !LATEST.equals(RUNNING)) {
            EntityPlayer player = login.player;
            player.addChatMessage(new ChatComponentText(String.format(Localization.get(MOD, "chat.updateMessage"), LATEST, RUNNING)));
            displayed = true;
        }
    }
}
