package enhanced.base.block;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;

public abstract class BlockContainerBase extends BlockContainer {
    public BlockContainerBase(String mod, String name, Material material, CreativeTabs tab, float hard) {
        super(material);
        setBlockName(name);
        setBlockTextureName(mod + ":" + name);
        setHardness(hard);
        
        if (tab != null)
            setCreativeTab(tab);
    }
}
