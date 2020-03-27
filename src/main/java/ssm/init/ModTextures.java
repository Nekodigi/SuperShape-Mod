package ssm.init;

import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.util.ResourceLocation;
import ssm.Main;

import java.util.ArrayList;
import java.util.List;

public class ModTextures {

    public static ResourceLocation makeRL(String dir){
        return new ResourceLocation(Main.MOD_ID + ":" + dir);
    }

    public static void register(TextureMap textureMap){
        textureMap.registerSprite(makeRL("blocks/small_tile"));
    }
}
