package enhanced.base.xmod;

import net.minecraft.item.ItemStack;
import cofh.api.energy.IEnergyContainerItem;

public class RedstoneFlux {
	public static boolean isEnergyContainer(ItemStack i) {
		return i != null && i.getItem() instanceof IEnergyContainerItem;
	}
}
