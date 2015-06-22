package enhanced.base.client.gui.elements;

import net.minecraft.item.ItemStack;

public interface IFakeSlotHandler {
    public boolean isItemValid(ItemStack s);

    public void onItemChanged(ItemStack newItem);
}
