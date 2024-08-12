package minmaximilian.reclaim.regen.util;

import java.util.Objects;

import javax.annotation.Nullable;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.block.state.BlockState;

public class BlockTracker {

    private final BlockState blockState;
    private final CompoundTag compoundTag;
    private final BlockPos blockPos;

    public BlockTracker(BlockState blockState, @Nullable CompoundTag compoundTag, BlockPos blockPos) {
        this.blockState = blockState;
        this.compoundTag = compoundTag;
        this.blockPos = blockPos.immutable();
    }

    public BlockState getBlockState() {
        return blockState;
    }

    public BlockPos getBlockPos() {
        return blockPos;
    }

    public CompoundTag getCompoundTag() {
        return compoundTag;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        BlockTracker that = (BlockTracker) o;
        return blockPos.equals(that.blockPos);
    }

    @Override
    public int hashCode() {
        return Objects.hash(blockPos);
    }
}
