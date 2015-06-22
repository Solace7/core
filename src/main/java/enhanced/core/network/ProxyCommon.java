package enhanced.core.network;

import enhanced.base.mod.BaseProxy;
import enhanced.core.EnhancedCore;

public class ProxyCommon extends BaseProxy {
	@Override
	public void registerBlocks() {
		
	}

	@Override
	public void registerItems() {
		
	}

	@Override
	public void registerTileEntities() {
		
	}

	@Override
	public void registerRecipes() {
		
	}

	@Override
	public void registerConfiguration() {
		EnhancedCore.instance.CHECK_FOR_UPDATES = config.get("General", "UpdateCheck", true, "Allow checking for updates from " + EnhancedCore.MOD_URL).getBoolean();
	}

	@Override
	public void registerPackets() {
		
	}
}
