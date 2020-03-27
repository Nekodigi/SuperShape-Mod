package ssm.tileentities;

import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.AxisAlignedBB;
import ssm.utils.IHasTESR;

public class TileEntitySuperShape extends TileEntity implements IHasTESR {
    @Override
    public TileEntitySpecialRenderer<TileEntitySuperShape> getTESR() {
        return new TESRSuperShape();
    }

    @Override
    public AxisAlignedBB getRenderBoundingBox() {
        return TileEntity.INFINITE_EXTENT_AABB;
    }
}
