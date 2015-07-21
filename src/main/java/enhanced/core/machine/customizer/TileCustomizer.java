package enhanced.core.machine.customizer;

import io.netty.buffer.ByteBuf;

import java.util.Map.Entry;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import enhanced.base.tile.TileMachineBase;
import enhanced.core.machine.RecipeMachine;

public class TileCustomizer extends TileMachineBase {
	public TileCustomizer() {
		super(35000);
	}
	
	@Override
	protected Entry<RecipeMachine, ItemStack> getRecipe() {
		return null;
	}

	@Override
	protected boolean hasEnoughComponents(RecipeMachine recipe) {
		return false;
	}

	@Override
	protected boolean hasEnoughExit(Entry<RecipeMachine, ItemStack> recipe) {
		return false;
	}

	@Override
	protected void handleRecipeCompleted(Entry<RecipeMachine, ItemStack> recipe) {
		
	}

	@Override
	protected void saveData(NBTTagCompound nbt) {
		
	}

	@Override
	protected void loadData(NBTTagCompound nbt) {
		
	}

    @Override
    public void writeToGui(ByteBuf buffer) {
        
    }
    
    @Override
    public void readFromGui(ByteBuf buffer) {
        
    }
}
