package com.example.examplemod.common;

import com.example.examplemod.blocks.TileEntityAnvilAluminium;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.world.World;

public class CommonProxy {
	public void registerTileEntity() {
		GameRegistry.registerTileEntity(TileEntityAnvilAluminium.class, "TileAnvilAluminium");
	}

//	public int getRenderID() {
//		return -1;
//	}

	public World getClientWorld() {
		return null;
	}
}
