package enhanced.base.manual;

public class PageCrafting extends PageManual {
    byte type; // 0 - Shapeless, 1 - Shaped, 2 - Furnace
    JsonItemStack[] input;
    JsonItemStack output;
    
    public PageCrafting(byte type2, JsonItemStack[] input2, JsonItemStack output2) {
        type = type2;
        input = input2;
        output = output2;
    }

    @Override
    public void render(boolean secondPage) {
        
    }

    @Override
    public void update() {
        
    }
}
