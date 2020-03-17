package ssm.tileentities;

import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import ssm.utils.IHasTESR;

public class TileEntitySuperShape extends TileEntity implements IHasTESR {
    @Override
    public TileEntitySpecialRenderer<TileEntitySuperShape> getTESR() {
        return new TESRSuperShape();
    }
}
