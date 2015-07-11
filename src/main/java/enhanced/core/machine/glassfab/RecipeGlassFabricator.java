package enhanced.core.machine.glassfab;

import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;
import enhanced.base.utilities.ComparableItemStack;
import enhanced.core.machine.RecipeMachine;

public class RecipeGlassFabricator extends RecipeMachine {
	FluidStack fluid;
	ComparableItemStack input1;
	ComparableItemStack input2;
	
	public RecipeGlassFabricator(ItemStack a, ItemStack b, FluidStack f, int e) {
		super(e);
		fluid = f;
		
		if (a != null)
			input1 = new ComparableItemStack(a);
		
		if (b != null)
			input2 = new ComparableItemStack(b);
	}
	
	public RecipeGlassFabricator(ComparableItemStack a, ComparableItemStack b, FluidStack f, int e) {
		super(e);
		input1 = a;
		input2 = b;
		fluid = f;
	}
	
	public boolean isFirstItem(ItemStack a) {
		if (input1 == null)
			return a == null;
		else if (a == null)
			return false;
		
		return input1.matchesItem(a);
	}
	
	public boolean isSecondItem(ItemStack a) {
		if (input2 == null)
			return a == null;
		else if (a == null)
			return false;
		
		return input2.matchesItem(a);
	}
	
	public boolean isFluid(FluidStack f) {
		if (fluid == null)
			return true;
		else if (f == null)
			return false;
		
		return fluid.equals(f);
	}
}
