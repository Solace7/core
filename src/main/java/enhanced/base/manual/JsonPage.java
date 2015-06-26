package enhanced.base.manual;

import enhanced.base.manual.PageTableOfContents.TOCEntry;
import enhanced.core.EnhancedCore;

public class JsonPage {
    // General
    String pageType;
    String nextPage;
    String prevPage;
    String pageName;

    // Table of Contents
    String title;
    TOCEntry[] entries;

    // Text
    String text;

    // Crafting
    byte type; // 0 - Shapeless, 1 - Shaped, 2 - Furnace
    JsonItemStack[] input;
    JsonItemStack output;

    public String getPageName() {
        return pageName;
    }
    
    public PageManual getPage() {
        if (pageName == null || pageName.isEmpty()) {
            EnhancedCore.instance.getLogger().warn("Missing required page name for manual page.");
            return null;
        } else if (pageType == null || pageType.isEmpty()) {
            EnhancedCore.instance.getLogger().warn("Missing required page type for manual page.");
            return null;
        }
        
        if (pageType.equals("toc")) {
            if (title != null && entries != null)
                return new PageTableOfContents(title, entries).setNextPage(nextPage).setPreviousPage(prevPage);
        } else if (pageType.equals("text")) {
            if (text != null)
                return new PageText(title, text).setNextPage(nextPage).setPreviousPage(prevPage);
        } else if (pageType.equals("crafting"))
            if (input != null && output != null)
                return new PageCrafting(type, input, output).setNextPage(nextPage).setPreviousPage(prevPage);

        EnhancedCore.instance.getLogger().warn("Found page of type: " + pageType + ", but at least one argument was missing.");
        return null;
    }
}
