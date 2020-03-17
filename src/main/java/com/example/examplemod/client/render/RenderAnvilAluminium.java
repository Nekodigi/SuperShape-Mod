package com.example.examplemod.client.render;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

import com.example.examplemod.blocks.AnvilAluminium;
import com.example.examplemod.vector.Vector;

import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;

@SideOnly(Side.CLIENT)
public class RenderAnvilAluminium implements ISimpleBlockRenderingHandler
{

	public RenderAnvilAluminium() {
		super();
	}
	/**インベントリ内でブロックをレンダリングするメソッド。もしshouldRender3DInInventoryがfalseなら空でもいいかも。**/
	@Override
	public void renderInventoryBlock(Block block, int metadata, int modelId, RenderBlocks renderer)
	{
		if (modelId == this.getRenderId())
		{
		Tessellator tessellator = Tessellator.instance;
		GL11.glTranslatef(-0.5F, -0.5F, -0.5F);
		int NI = 50;
	    int NJ = 50;
	    double r = 0.5;
		Vector[][] ps = new Vector[NI][NJ+1];
		for(int i = 0; i < NI; i++) {
			double theta = 2d*Math.PI/NI*i-Math.PI;
			for(int j = 0; j <= NJ; j++) {
				double phi = Math.PI/NJ*j-Math.PI/2d;
				double r1 = supershape(theta, 0.2,1.7,1.7,7, 1, 1)*r;
				double r2 = supershape(phi, 0.2,1.7,1.7,7, 1, 1)*r;
				ps[i][j] = new Vector(r1*Math.cos(theta)*r2*Math.cos(phi), r2*Math.sin(phi), r1*Math.sin(theta)*r2*Math.cos(phi));
			}

		}
		IIcon icon = AnvilAluminium.sicon;
		double minU = icon.getMinU();
		double maxU = icon.getMaxU();
		double minV = icon.getMinV();
		double maxV = icon.getMaxV();
		for(int i = 0; i < NI; i++) {
			for(int j = 0; j < NJ; j++) {
				tessellator.addVertexWithUV(ps[i][j].x, ps[i][j].y, ps[i][j].z, minU, maxV);
				tessellator.addVertexWithUV(ps[i][j+1].x, ps[i][j+1].y, ps[i][j+1].z, minU, minV);
				tessellator.addVertexWithUV(ps[(i+1)%NI][j+1].x, ps[(i+1)%NI][j+1].y, ps[(i+1)%NI][j+1].z, maxU, minV);
				tessellator.addVertexWithUV(ps[(i+1)%NI][j].x, ps[(i+1)%NI][j].y, ps[(i+1)%NI][j].z, maxU, maxV);
			}
		}
		GL11.glTranslatef(0.5F, 0.5F, 0.5F);
		}
	}

	/**ワールド内でブロックをレンダリングするメソッド**/
	@Override
	public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks renderer)
	{
		if (modelId == this.getRenderId())
		{
			Tessellator tessellator = Tessellator.instance;
			GL11.glPushMatrix();
	        GL11.glEnable(GL12.GL_RESCALE_NORMAL);
//	        GL11.glEnable(GL11.GL_BLEND);
//	        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
	        GL11.glColor4f(2.0F, 2.0F, 2.0F, 0.75F);
	        GL11.glTranslatef((float)x, (float)y, (float)z);
	        GL11.glScalef(1.0F, -1.0F, -1.0F);
	        GL11.glRotatef(0.0F, 0.0F, 1.0F, 0.0F);
			int NI = 50;
		    int NJ = 50;
		    double r = 0.5;
			Vector[][] ps = new Vector[NI][NJ+1];
			for(int i = 0; i < NI; i++) {
				double theta = 2d*Math.PI/NI*i-Math.PI;
				for(int j = 0; j <= NJ; j++) {
					double phi = Math.PI/NJ*j-Math.PI/2d;
					double r1 = supershape(theta, 0.2,1.7,1.7,7, 1, 1)*r;
					double r2 = supershape(phi, 0.2,1.7,1.7,7, 1, 1)*r;
					ps[i][j] = new Vector(r1*Math.cos(theta)*r2*Math.cos(phi), r2*Math.sin(phi), r1*Math.sin(theta)*r2*Math.cos(phi));
				}

			}
			IIcon icon = AnvilAluminium.sicon;
			double minU = icon.getMinU();
			double maxU = icon.getMaxU();
			double minV = icon.getMinV();
			double maxV = icon.getMaxV();
			for(int i = 0; i < NI; i++) {
				for(int j = 0; j < NJ; j++) {
					tessellator.addVertexWithUV(ps[i][j].x, ps[i][j].y, ps[i][j].z, minU, maxV);
					tessellator.addVertexWithUV(ps[i][j+1].x, ps[i][j+1].y, ps[i][j+1].z, minU, minV);
					tessellator.addVertexWithUV(ps[(i+1)%NI][j+1].x, ps[(i+1)%NI][j+1].y, ps[(i+1)%NI][j+1].z, maxU, minV);
					tessellator.addVertexWithUV(ps[(i+1)%NI][j].x, ps[(i+1)%NI][j].y, ps[(i+1)%NI][j].z, maxU, maxV);
				}
			}
			GL11.glTranslatef(0.5F, 0.5F, 0.5F);
			return true;
		}
		return false;
	}

	@Override
	/**インベントリ内で3Dレンダリングするか否かを返すメソッド**/
	public boolean shouldRender3DInInventory(int modelId) {
		return false;
	}

	/**自身のレンダーIDを返すメソッド**/
	@Override
	public int getRenderId() {

		return -1;
	}

	protected float renderAlumiAnvil(Block block , RenderBlocks renderer , int metadata , float sides1 , float sides2 , float sides3  , float sides4 , boolean flg)
	{
		/**詳しい説明はカット。ここは難しいため、基本的にはshouldRender3DInI)nventoryをfalseにすることをお勧めする。**/
		if(flg)
		{
			float f = sides2;
			sides2 = sides4;
			sides4 =f;
		}
		sides2 /= 2.0F;
        sides4 /= 2.0F;
		renderer.setRenderBounds((double)(0.5F - sides2), (double)sides1, (double)(0.5F - sides4), (double)(0.5F + sides2), (double)(sides1 + sides3), (double)(0.5F + sides4));

		Tessellator tessellator = Tessellator.instance;
		GL11.glTranslatef(-0.5F, -0.5F, -0.5F);
		tessellator.startDrawingQuads();
		tessellator.setNormal(0.0F, -1.0F, 0.0F);
		renderer.renderFaceYNeg(block, 0.0D, 0.0D, 0.0D, renderer.getBlockIconFromSideAndMetadata(block, 0, metadata));
		tessellator.draw();
		tessellator.startDrawingQuads();
		tessellator.setNormal(0.0F, 1.0F, 0.0F);
		renderer.renderFaceYPos(block, 0.0D, 0.0D, 0.0D, renderer.getBlockIconFromSideAndMetadata(block, 1, metadata));
		tessellator.draw();
		tessellator.startDrawingQuads();
		tessellator.setNormal(0.0F, 0.0F, -1.0F);
		renderer.renderFaceZNeg(block, 0.0D, 0.0D, 0.0D, renderer.getBlockIconFromSideAndMetadata(block, 2, metadata));
		tessellator.draw();
		tessellator.startDrawingQuads();
		tessellator.setNormal(0.0F, 0.0F, 1.0F);
		renderer.renderFaceZPos(block, 0.0D, 0.0D, 0.0D, renderer.getBlockIconFromSideAndMetadata(block, 3, metadata));
		tessellator.draw();
		tessellator.startDrawingQuads();
		tessellator.setNormal(-1.0F, 0.0F, 0.0F);
		renderer.renderFaceXNeg(block, 0.0D, 0.0D, 0.0D, renderer.getBlockIconFromSideAndMetadata(block, 4, metadata));
		tessellator.draw();
		tessellator.startDrawingQuads();
		tessellator.setNormal(1.0F, 0.0F, 0.0F);
		renderer.renderFaceXPos(block, 0.0D, 0.0D, 0.0D, renderer.getBlockIconFromSideAndMetadata(block, 5, metadata));
		tessellator.draw();
		GL11.glTranslatef(0.5F, 0.5F, 0.5F);
		renderer.setRenderBounds(0.0D, 0.0D, 0.0D, 1.0D, 1.0D, 1.0D);
		return sides1 + sides3;
	}

	double supershape(double theta, double n1, double n2, double n3, double m, double a, double b) {
		double t1 = Math.pow(Math.abs(Math.cos(m*theta/4)/a), n2);
		double t2 = Math.pow(Math.abs(Math.sin(m*theta/4)/b), n3);
		return Math.pow(t1 + t2, -1/n1);
	}
}
