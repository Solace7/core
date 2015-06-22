package enhanced.base.mod;

import java.io.File;

import net.minecraftforge.common.config.Configuration;

public abstract class BaseProxy {
	protected Configuration config;
	
	public void init() {
		
	}
	
	public void preInit(File file) {
		config = new Configuration(file);
		registerConfiguration();
		config.save();
	}
	
	public void postInit() {
		
	}
	
	public abstract void registerBlocks();
	public abstract void registerItems();
	public abstract void registerTileEntities();
	public abstract void registerRecipes();
	protected abstract void registerConfiguration();
	public abstract void registerPackets();
	
	public void registerPotions() {
		
	}
}
