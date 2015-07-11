package enhanced.base.utilities;

import java.util.HashMap;

import net.minecraft.item.ItemStack;

public class CraftingHandler {
    HashMap<ComparableItemStack, HashMap<ComparableItemStack, ItemStack>> map = new HashMap<ComparableItemStack, HashMap<ComparableItemStack, ItemStack>>();
    
    HashMap<ComparableItemStack, ItemStack> getAllEntries(ComparableItemStack a) {
        return map.get(a);
    }
    
    public ItemStack getResult(ComparableItemStack a, ComparableItemStack b) {
        HashMap<ComparableItemStack, ItemStack> second = getAllEntries(a);
        return second == null ? null : second.get(b);
    }
    
    public ItemStack getResult(ItemStack a, ItemStack b) {
        return getResult(new ComparableItemStack(a), new ComparableItemStack(b));
    }
    
    public boolean canAcceptItem(ComparableItemStack stack, ComparableItemStack stack2) {
        if (stack == null && stack2 == null) return true;
        if (stack == null && stack2 != null) return false;
        
        if (stack2 == null) {
            return map.containsKey(stack);
        }
        
        HashMap<ComparableItemStack, ItemStack> list = getAllEntries(stack);
        if (list == null) return false;
        
        return list.containsKey(stack2);
    }

    public boolean canAcceptItem(int slot, ItemStack stack) {
        // TODO Auto-generated method stub
        return false;
    }
}
