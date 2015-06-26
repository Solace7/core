package enhanced.base.manual;

import net.minecraft.client.Minecraft;

public class PageTableOfContents extends PageManual {
    public class TOCEntry {
        String text;
        String link;
    }
    
    String title;
    TOCEntry[] entries;

    public PageTableOfContents(String t, TOCEntry[] e) {
        title = t;
        entries = e;
    }
    
    @Override
    public void render(boolean secondPage) {
        int x = secondPage ? 150 : 20, y = 30;
        
        Minecraft.getMinecraft().fontRenderer.drawString(title, x, 15, 0xAA0000);
        int count = 0;
        
        for (TOCEntry e : entries) {
            if (!e.text.isEmpty()) {
                if (e.link.equals("HEADER")) {
                    Minecraft.getMinecraft().fontRenderer.drawString(e.text, x, y + (count * 10), 0xAA0000);
                    y += 5;
                } else {
                    Minecraft.getMinecraft().fontRenderer.drawString(":", x, y + (count * 10), 0xAA0000);
                    Minecraft.getMinecraft().fontRenderer.drawString(e.text, x + 5, y + (count * 10), 0x000000);
                }
            }
            
            count++;
        }
    }

    @Override
    public void update() {

    }
}
