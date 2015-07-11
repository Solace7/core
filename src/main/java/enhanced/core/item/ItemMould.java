package enhanced.core.item;

import java.util.List;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import enhanced.base.item.ItemBase;
import enhanced.core.EnhancedCore;
import enhanced.core.Reference.ECMod;

public class ItemMould extends ItemBase {
	static String[] names = { "block", "pane" };
	static IIcon[] icons = new IIcon[names.length];

	public ItemMould(String n) {
		super(ECMod.ID, n, EnhancedCore.instance.creativeTab);
		setMaxStackSize(1);
		setHasSubtypes(true);
		setMaxDamage(0);
	}
	
	@Override
	public IIcon getIconFromDamage(int meta) {
		if (meta < 0 || meta >= names.length)
			meta = 0;
		
		return icons[meta];
	}
	
	@Override
	public void registerIcons(IIconRegister register) {
		for (int i = 0; i < names.length; i++) {
			icons[i] = register.registerIcon(getIconString() + "_" + i);
		}
	}

	@Override
	public void getSubItems(Item item, CreativeTabs creativeTab, List list) {
		for (int i = 0; i < names.length; i++) {
			list.add(new ItemStack(item, 1, i));
		}
	}
	
	@Override
	public String getUnlocalizedName(ItemStack stack) {
		String name = ".unknown";
		
		if (stack.getItemDamage() >= 0 && stack.getItemDamage() < names.length)
			name = "." + names[stack.getItemDamage()];
		
		return super.getUnlocalizedName(stack) + name;
	}
}
