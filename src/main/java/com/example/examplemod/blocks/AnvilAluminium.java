package com.example.examplemod.blocks;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class AnvilAluminium extends Block implements ITileEntityProvider{
	public static IIcon sicon;

	public AnvilAluminium() {
		super(Material.rock);
		setCreativeTab(CreativeTabs.tabBlock);
		setBlockName("AnvilAluminum");
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister par1IconRegister)
    {
        this.sicon = par1IconRegister.registerIcon("examplemod:Test");
    }

	@SideOnly(Side.CLIENT)
    public IIcon getIcon(int par1, int par2)
    {
		return sicon;
    }

	@Override
	public TileEntity createNewTileEntity(World world, int meta) {
		return new TileEntityAnvilAluminium();
	}

		/**レンダーIDを返す。**/
		@Override
		public int getRenderType()
		{
			return -1;
		}

		/**ブロックが透けるか否かを返す。**/
	    @SideOnly(Side.CLIENT)
	    public boolean shouldSideBeRendered(IBlockAccess p_149646_1_, int p_149646_2_, int p_149646_3_, int p_149646_4_, int p_149646_5_)
	    {
	        return true;
	    }

	    /**通常のブロックでないかを返す。*/
	    public boolean renderAsNormalBlock()
	    {
	        return false;
	    }

	    /**ブロックが透明か否かを返す。*/
	    public boolean isOpaqueCube()
	    {
	        return false;
	    }
}