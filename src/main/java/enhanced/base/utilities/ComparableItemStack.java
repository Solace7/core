package enhanced.base.utilities;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.oredict.OreDictionary;

public class ComparableItemStack {
    Item item;
    int stackSize;
    int metadata;
    NBTTagCompound tag;
    int oreID = -1;

    public ComparableItemStack(ItemStack s) {
        set(s);
    }

    public ComparableItemStack(Item i, int count) {
        set(new ItemStack(i, count));
    }

    public ComparableItemStack(Item i, int count, int meta) {
        set(new ItemStack(i, count, meta));
    }

    public ComparableItemStack(Item i, int count, int meta, NBTTagCompound tag) {
        ItemStack s = new ItemStack(i, count, meta);
        s.setTagCompound(tag);
        set(s);
    }

    public ComparableItemStack(Block b, int count) {
        set(new ItemStack(b, count));
    }

    public ComparableItemStack(Block b, int count, int meta) {
        set(new ItemStack(b, count, meta));
    }

    public ComparableItemStack(Block b, int count, int meta, NBTTagCompound tag) {
        ItemStack s = new ItemStack(b, count, meta);
        s.setTagCompound(tag);
        set(s);
    }

    public void set(ItemStack stack) {
        item = stack.getItem();
        stackSize = stack.stackSize;
        metadata = stack.getItemDamage();
        tag = stack.getTagCompound();
    }

    public int getOreId() {
        return oreID;
    }

    @Override
    public int hashCode() {
        return 0;
    }

    public boolean matches(ComparableItemStack stack) {
        return matchesItem(stack) && stackSize == stack.stackSize;
    }
    
    public boolean matchesItem(ComparableItemStack stack) {
        if (getOreId() != -1 && stack.getOreId() == getOreId())
            return true;

        return item == stack.item && (metadata == OreDictionary.WILDCARD_VALUE || stack.metadata == OreDictionary.WILDCARD_VALUE || metadata == stack.metadata) && matchesTag(stack.tag);
    }
    
    public boolean matchesItem(ItemStack stack) {
        return matchesItem(new ComparableItemStack(stack));
    }

    boolean matchesTag(NBTTagCompound t) {
        if (tag == null && t == null)
            return true;

        if (tag != null)
            return tag.equals(t);
        
        return false;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof ComparableItemStack)
            return matches((ComparableItemStack) obj);
        else if (obj instanceof ItemStack)
        	return matches(new ComparableItemStack((ItemStack) obj));

        return false;
    }

	public int getStackSize() {
		return stackSize;
	}
}
