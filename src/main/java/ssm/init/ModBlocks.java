package ssm.init;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.registries.IForgeRegistry;
import ssm.Main;
import ssm.blocks.BlockSuperShape;
import ssm.utils.IHasTESR;
import ssm.utils.IHasTileEntity;

import java.util.ArrayList;
import java.util.List;

public class ModBlocks {
    public static final List<Block> BLOCKS = new ArrayList<Block>();

    public static final BlockSuperShape SuperShapeBlock = new BlockSuperShape("supershape_block", Material.IRON);

    public static void register(IForgeRegistry<Block> registry) {
        registry.registerAll(BLOCKS.toArray(new Block[0]));
        for (Block block : BLOCKS) {
            if (block instanceof IHasTileEntity) {
                IHasTileEntity IHTE = ((IHasTileEntity) block);
                Class TEClass = IHTE.getTileEntityClass();
                GameRegistry.registerTileEntity(TEClass, new ResourceLocation(Main.MOD_ID + ":" + block.getRegistryName().toString()));
                TileEntity TE = IHTE.createTileEntity();
                if(TE instanceof IHasTESR){
                    Main.proxy.registerRenderers(TEClass, ((IHasTESR) TE).getTESR());
                }
            }
        }
    }
}
