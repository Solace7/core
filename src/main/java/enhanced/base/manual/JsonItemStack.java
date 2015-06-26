package enhanced.base.manual;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.item.ItemStack;
import enhanced.core.EnhancedCore;

public class JsonItemStack {
    String name;
    int metadata;
    
    public ItemStack getItemStack() {
        if (name == null) {
            EnhancedCore.instance.getLogger().warn("Error processing JsonItemStack. Name is null");
            return null;
        }
        
        ItemStack stack = null;
        
        if (name.contains(":")) {
            String[] split = name.split(":");
            stack = GameRegistry.findItemStack(split[0], split[1], 1);
        } else {
            stack = GameRegistry.findItemStack("minecraft", name, 1);
        }
        
        if (stack == null) {
            EnhancedCore.instance.getLogger().warn("Error processing JsonItemStack. Could not find an item registered with the name: " + name);
            return null;
        }
        
        return stack;
    }
}
