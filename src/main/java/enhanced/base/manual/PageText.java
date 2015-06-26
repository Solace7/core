package enhanced.base.manual;

import net.minecraft.client.Minecraft;

public class PageText extends PageManual {
    String title;
    String text;

    public PageText(String ti, String t) {
        title = ti;
        text = t;
    }
    
    @Override
    public void render(boolean secondPage) {
        int x = secondPage ? 150 : 20, y = 15;
        
        if (title != null) {
            Minecraft.getMinecraft().fontRenderer.drawString(title, x, 15, 0xAA0000);
            y = 30;
        } else
            Minecraft.getMinecraft().fontRenderer.drawString("", 0, 0, 0xAA0000);
        
        Minecraft.getMinecraft().fontRenderer.drawSplitString(text, x, y, 110, 0x000000);
    }

    @Override
    public void update() {

    }
}
