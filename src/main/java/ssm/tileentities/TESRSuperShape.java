package ssm.tileentities;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.block.model.IBakedModel;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.ForgeHooksClient;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;
import ssm.proxy.ClientProxy;
import ssm.utils.vector.Vector;
import ssm.utils.vector.VectorUV;

public class TESRSuperShape extends TileEntitySpecialRenderer<TileEntitySuperShape>{
    @Override
    public void render(TileEntitySuperShape te, double x, double y, double z, float partialTicks, int destroyStage, float alpha) {
        double fac = (double)te.getWorld().getTotalWorldTime()/100;
            GlStateManager.enableRescaleNormal();
            //GL11.glEnable(GL12.GL_RESCALE_NORMAL);
            GL11.glColor4f(1, 1, 1, 1);
            GlStateManager.disableLighting();
            GlStateManager.pushMatrix();
            GlStateManager.translate(x + 0.5, y + 2, z + 0.5);

            Tessellator tessellator = Tessellator.getInstance();
            BufferBuilder bufferBuilder = tessellator.getBuffer();
            TextureAtlasSprite icon = Minecraft.getMinecraft().getTextureMapBlocks().getAtlasSprite("ssm:blocks/small_tile");
            double deltaU = icon.getMaxU() - icon.getMinU();
            double deltaV = icon.getMaxV() - icon.getMinV();
            int NI = 50;
            int NJ = 50;
            double r = 1;
            VectorUV[][] ps = new VectorUV[NI][NJ+1];
            for(int i = 0; i < NI; i++) {
                double theta = 2d*Math.PI*i/NI-Math.PI;
                double u = icon.getMinU()+deltaU*i/NI;
                for(int j = 0; j <= NJ; j++) {
                    double phi = Math.PI*j/NJ-Math.PI/2d;
                    double v = icon.getMinV()+deltaV*j/NJ;
                    double r1 = supershape(theta, 0.2,1.7,1.7,Math.sin(fac)*4+4, 1, 1)*r;
                    double r2 = supershape(phi, 0.2,1.7,1.7,Math.sin(fac)*4+4, 1, 1)*r;
                    ps[i][j] = new VectorUV(r1*Math.cos(theta)*r2*Math.cos(phi), r2*Math.sin(phi), r1*Math.sin(theta)*r2*Math.cos(phi), u, v);
                }

            }

            Minecraft.getMinecraft().getTextureManager().bindTexture(TextureMap.LOCATION_BLOCKS_TEXTURE);
            for(int i = 0; i < NI; i++) {
                for (int j = 0; j < NJ; j++) {
                    bufferBuilder.begin(GL11.GL_QUADS, DefaultVertexFormats.POSITION_TEX);
                    VectorUV p1 = ps[i][j];
                    VectorUV p2 = ps[i][j + 1];
                    VectorUV p3 = ps[(i + 1) % NI][j + 1];
                    VectorUV p4 = ps[(i + 1) % NI][j];
                    bufferBuilder.pos(p1.x, p1.y, p1.z).tex(icon.getMinU(), icon.getMaxV()).endVertex();
                    bufferBuilder.pos(p2.x, p2.y, p2.z).tex(icon.getMinU(), icon.getMinV()).endVertex();
                    bufferBuilder.pos(p3.x, p3.y, p3.z).tex(icon.getMaxU(), icon.getMinV()).endVertex();
                    bufferBuilder.pos(p4.x, p4.y, p4.z).tex(icon.getMaxU(), icon.getMaxV()).endVertex();
                    tessellator.draw();
                }
            }

            //GL11.glEnable(GL12.GL_RESCALE_NORMAL);
            GlStateManager.popMatrix();
            GlStateManager.enableLighting();
            GlStateManager.disableRescaleNormal();
    }

    double supershape(double theta, double n1, double n2, double n3, double m, double a, double b) {
        double t1 = Math.pow(Math.abs(Math.cos(m*theta/4)/a), n2);
        double t2 = Math.pow(Math.abs(Math.sin(m*theta/4)/b), n3);
        return Math.pow(t1 + t2, -1/n1);
    }
}
