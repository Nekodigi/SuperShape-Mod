package ssm.utils;

import net.minecraft.block.state.IBlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public interface IHasTileEntity<TE extends TileEntity> {
    public Class<TE> getTileEntityClass();

    public TE createTileEntity();
}
