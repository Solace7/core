package enhanced.core.machine;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlockWithMetadata;
import net.minecraft.item.ItemStack;

public class ItemMachine extends ItemBlockWithMetadata {
	public ItemMachine(Block b) {
		super(b, b);
	}

	@Override
	public String getUnlocalizedName(ItemStack stack) {
		String name = ".unknown";
		
		if (stack.getItemDamage() == 0)
			name = ".glassFab";
		else if (stack.getItemDamage() == 1)
			name = ".customizer";
		
		return super.getUnlocalizedName(stack) + name;
	}
}
