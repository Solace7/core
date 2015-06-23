package enhanced.base.client.gui.tabs;

import java.util.List;

import enhanced.base.client.gui.BaseGui;
import enhanced.base.utilities.Localization;

public class TabTip extends BaseTab {
    String tip;

    @SuppressWarnings("rawtypes")
    public TabTip(BaseGui gui, String n, String mod) {
        super(gui, 0);
        name = Localization.get(mod, "tab." + n);
        tip = Localization.get(mod, "tab." + n + ".info").replace("<NL>", "\n").replaceAll("<([^<]*)>", "\u00A7$1");
        List l = gui.getFontRenderer().listFormattedStringToWidth(tip, maxWidth - 14);
        maxHeight = l.size() * gui.getFontRenderer().FONT_HEIGHT + 25;
        backgroundColor = 0x33AA00;
    }

    @Override
    public void drawFullyOpened() {
        parent.getFontRenderer().drawSplitString(tip, posX - maxWidth + 7, posY + 20, maxWidth - 14, 0x000000);
    }

    @Override
    public void drawFullyClosed() {

    }
}
