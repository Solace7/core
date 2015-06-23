package enhanced.base.mod;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class CreativeTab extends CreativeTabs {
    ItemStack item;

    public CreativeTab(String name, ItemStack item) {
        super(name);
        this.item = item;
    }

    public CreativeTab(String name) {
        super(name);
    }

    @Override
    public ItemStack getIconItemStack() {
        return item == null ? new ItemStack(Blocks.fire) : item;
    }

    @Override
    public Item getTabIconItem() {
        return null;
    }

    public CreativeTab setItem(ItemStack stack) {
        item = stack;
        return this;
    }
}
