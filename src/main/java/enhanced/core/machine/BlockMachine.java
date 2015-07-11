package enhanced.core.machine;

import java.util.HashMap;
import java.util.List;

import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import enhanced.base.block.BlockMachineBase;
import enhanced.base.tile.TileMachineBase;
import enhanced.core.EnhancedCore;
import enhanced.core.Reference.ECGui;
import enhanced.core.Reference.ECMod;
import enhanced.core.machine.customizer.TileCustomizer;
import enhanced.core.machine.glassfab.RecipeGlassFabricator;
import enhanced.core.machine.glassfab.TileGlassFabricator;

public class BlockMachine extends BlockMachineBase {
	public static HashMap<ItemStack, RecipeGlassFabricator> recipes;
	IIcon[] icons = new IIcon[2];
	
    public BlockMachine(String name) {
        super(ECMod.ID, EnhancedCore.instance, name, Material.rock, EnhancedCore.instance.creativeTab, 3f);
    }

    @Override
    public TileEntity createNewTileEntity(World world, int meta) {
    	if (meta == 0)
    		return new TileGlassFabricator();
    	else if (meta == 1)
    		return new TileCustomizer();
    	
    	return null;
    }
    
    @Override
    public IIcon getIcon(IBlockAccess blockAccess, int x, int y, int z, int s) {
        TileEntity tile = blockAccess.getTileEntity(x, y, z);

        if (tile instanceof TileMachineBase) {
            TileMachineBase machine = (TileMachineBase) tile;

            if (s == machine.facing.ordinal())
                return icons[blockAccess.getBlockMetadata(x, y, z)];
        }

        return sideTexture;
    }

    @Override
    public IIcon getIcon(int s, int m) {
    	if (m < 0 || m >= icons.length)
    		m = 0;
    	
        return s == 4 ? icons[m] : sideTexture;
    }

    @Override
    public void registerBlockIcons(IIconRegister register) {
        sideTexture = register.registerIcon(ECMod.ID + ":machine_side");
        
        for (int i = 0; i < icons.length; i++) {
        	icons[i] = register.registerIcon(ECMod.ID + ":machine_" + i);
        }
    }

    @Override
    protected int getGui(int meta, EntityPlayer player) {
    	if (meta == 0)
    		return ECGui.glassFabricator.ordinal();
    	else if (meta == 1)
    		return ECGui.customizer.ordinal();
    	
    	return -1;
    }
    
    @Override
    public void getSubBlocks(Item item, CreativeTabs creativeTab, List list) {
    	for (int i = 0; i < icons.length; i++) {
    		list.add(new ItemStack(item, 1, i));
    	}
    }
}
