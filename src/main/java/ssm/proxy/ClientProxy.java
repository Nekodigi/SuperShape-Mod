package ssm.proxy;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.client.renderer.vertex.VertexFormat;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.client.registry.ClientRegistry;

public class ClientProxy extends CommonProxy{
    public static final VertexFormat POSITION_TEX_LMAP_NORMAL =
            new VertexFormat()
                    .addElement(DefaultVertexFormats.POSITION_3F)
                    .addElement(DefaultVertexFormats.TEX_2F)
                    .addElement(DefaultVertexFormats.TEX_2S)
                    .addElement(DefaultVertexFormats.NORMAL_3B);

    public void registerItemRenderer(Item item, int meta, String id){
        ModelLoader.setCustomModelResourceLocation(item, meta, new ModelResourceLocation(item.getRegistryName(), id));
    }

    public void registerRenderers(Class TEClass, TileEntitySpecialRenderer TESRIns){
        ClientRegistry.bindTileEntitySpecialRenderer(TEClass, TESRIns);
    }
}
