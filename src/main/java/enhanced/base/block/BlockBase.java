package enhanced.base.block;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;

public class BlockBase extends Block {
    protected BlockBase(String name, Material material, CreativeTabs tab, float hard) {
        super(material);
        setBlockName(name);
        setCreativeTab(tab);
        setHardness(hard);
    }
}
