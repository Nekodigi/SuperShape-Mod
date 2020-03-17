package com.example.examplemod.client.render;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

import com.example.examplemod.blocks.AnvilAluminium;
import com.example.examplemod.vector.Vector;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;

@SideOnly(Side.CLIENT)
public class TileEntityRenderAnvilAluminium extends TileEntitySpecialRenderer{
	public static TileEntityRenderAnvilAluminium renderer;

	public TileEntityRenderAnvilAluminium() {
		super();
	}

	public void setTileEntityRenderer(TileEntityRendererDispatcher parTileEntityRenderer) {
		super.func_147497_a(parTileEntityRenderer);
		renderer = this;
	}

	@Override
	public void renderTileEntityAt(TileEntity tileEntity, double x, double y, double z, float f) {
		double fac = (double)Minecraft.getSystemTime()/1000;
		Tessellator tessellator = Tessellator.instance;
		GL11.glPushMatrix();
        GL11.glEnable(GL12.GL_RESCALE_NORMAL);
//        GL11.glEnable(GL11.GL_BLEND);
//        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        GL11.glColor4f(2.0F, 2.0F, 2.0F, 0.75F);
        GL11.glTranslatef((float)x+0.5f, (float)y+0.5f, (float)z+0.5f);
        GL11.glScalef(1.0F, -1.0F, -1.0F);
        GL11.glRotatef(0.0F, 0.0F, 1.0F, 0.0F);
		int NI = 50;
	    int NJ = 50;
	    double r = 1;
		Vector[][] ps = new Vector[NI][NJ+1];
		for(int i = 0; i < NI; i++) {
			double theta = 2d*Math.PI/NI*i-Math.PI;
			for(int j = 0; j <= NJ; j++) {
				double phi = Math.PI/NJ*j-Math.PI/2d;
				double r1 = supershape(theta, 0.2,1.7,1.7,Math.sin(fac)*4+4, 1, 1)*r;
				double r2 = supershape(phi, 0.2,1.7,1.7,Math.sin(fac)*4+4, 1, 1)*r;
				ps[i][j] = new Vector(r1*Math.cos(theta)*r2*Math.cos(phi), r2*Math.sin(phi), r1*Math.sin(theta)*r2*Math.cos(phi));
			}

		}
		IIcon icon = AnvilAluminium.sicon;
		double minU = icon.getMinU();
		double maxU = icon.getMaxU();
		double minV = icon.getMinV();
		double maxV = icon.getMaxV();
		this.bindTexture(TextureMap.locationBlocksTexture);
		for(int i = 0; i < NI; i++) {
			for(int j = 0; j < NJ; j++) {
				tessellator.startDrawingQuads();
				tessellator.addVertexWithUV(ps[i][j].x, ps[i][j].y, ps[i][j].z, minU, maxV);
				tessellator.addVertexWithUV(ps[i][j+1].x, ps[i][j+1].y, ps[i][j+1].z, minU, minV);
				tessellator.addVertexWithUV(ps[(i+1)%NI][j+1].x, ps[(i+1)%NI][j+1].y, ps[(i+1)%NI][j+1].z, maxU, minV);
				tessellator.addVertexWithUV(ps[(i+1)%NI][j].x, ps[(i+1)%NI][j].y, ps[(i+1)%NI][j].z, maxU, maxV);
//				tessellator.addVertex(ps[i][j+1].x, ps[i][j+1].y, ps[i][j+1].z);
//				tessellator.addVertex(ps[i][j].x, ps[i][j].y, ps[i][j].z);
//				tessellator.addVertex(ps[(i+1)%NI][j].x, ps[(i+1)%NI][j].y, ps[(i+1)%NI][j].z);
//				tessellator.addVertex(ps[(i+1)%NI][j+1].x, ps[(i+1)%NI][j+1].y, ps[(i+1)%NI][j+1].z);
				tessellator.draw();
			}
		}
		GL11.glDisable(GL12.GL_RESCALE_NORMAL);
//      GL11.glDisable(GL11.GL_BLEND);
		GL11.glPopMatrix();
	}

	double supershape(double theta, double n1, double n2, double n3, double m, double a, double b) {
		double t1 = Math.pow(Math.abs(Math.cos(m*theta/4)/a), n2);
		double t2 = Math.pow(Math.abs(Math.sin(m*theta/4)/b), n3);
		return Math.pow(t1 + t2, -1/n1);
	}
}