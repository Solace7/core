package enhanced.base.item;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class ItemBase extends Item {
    public ItemBase(String m, String n, CreativeTabs tab) {
        super();
        setUnlocalizedName(n);
        setTextureName(m + ":" + n);
        
        if (tab != null)
            setCreativeTab(tab);
    }
}
