package com.example.examplemod.client;

import com.example.examplemod.blocks.TileEntityAnvilAluminium;
import com.example.examplemod.client.render.TileEntityRenderAnvilAluminium;
import com.example.examplemod.common.CommonProxy;

import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.client.registry.ClientRegistry;
import net.minecraft.world.World;

public class ClientProxy extends CommonProxy{
	@Override
	public void registerTileEntity() {
		//RenderingRegistry.registerBlockHandler(new RenderAnvilAluminium());
		ClientRegistry.registerTileEntity(TileEntityAnvilAluminium.class, "TileAnvilAluminium", new TileEntityRenderAnvilAluminium());
	}
	
//	@Override
//	public int getRenderID() {
//		return RenderingRegistry.getNextAvailableRenderId();
//	}
	
	@Override
	public World getClientWorld() {
		return FMLClientHandler.instance().getClient().theWorld;
	}
}
