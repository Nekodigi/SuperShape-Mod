package ssm.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import ssm.Main;
import ssm.init.ModBlocks;
import ssm.init.ModItems;
import ssm.utils.IHasModel;

public class BlockBase extends Block implements IHasModel {
    public static Item item;
    public BlockBase(String name, Material material){
        super(material);
        setTranslationKey(name);
        setRegistryName(name);
        setCreativeTab(CreativeTabs.BUILDING_BLOCKS);

        ModBlocks.BLOCKS.add(this);
        item = new ItemBlock(this).setRegistryName(this.getRegistryName());
        ModItems.ITEMS.add(item);
    }

    public void registerModels(){
        Main.proxy.registerItemRenderer(Item.getItemFromBlock(this), 0, "inventory");
    }
}
