package ssm.proxy;

import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.item.Item;
import net.minecraftforge.fml.client.registry.ClientRegistry;

public class CommonProxy {
    public void registerItemRenderer(Item item, int meta, String id){}

    public void registerRenderers(Class TEClass, TileEntitySpecialRenderer TESRIns){}
}
