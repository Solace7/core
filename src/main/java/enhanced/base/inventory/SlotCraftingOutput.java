package enhanced.base.inventory;

import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class SlotCraftingOutput extends Slot {
    public SlotCraftingOutput(IInventory iInventory, int slot, int x, int y) {
        super(iInventory, slot, x, y);
    }
    
    @Override
    public boolean isItemValid(ItemStack stack) {
        return false;
    }
}
