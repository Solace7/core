package enhanced.base.manual;

import net.minecraft.client.gui.Gui;

public abstract class PageManual extends Gui {
    String nextPage = null;
    String prevPage = null;
    
    public abstract void render(boolean secondPage);
    public abstract void update();
    
    public PageManual setNextPage(String next) {
        nextPage = next;
        return this;
    }
    
    public PageManual setPreviousPage(String prev) {
        prevPage = prev;
        return this;
    }
    
    public boolean hasNextPage() {
        return nextPage != null && !nextPage.isEmpty();
    }
    
    public boolean hasPrevPage() {
        return prevPage != null && !prevPage.isEmpty();
    }
    
    public String getNextPage() {
        return nextPage;
    }
    
    public String getPrevPage() {
        return prevPage;
    }
}
