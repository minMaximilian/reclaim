package minmaximilian.pvp_enhancements.regen.util;

import java.util.Objects;

import javax.annotation.Nullable;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.block.state.BlockState;

public class BlockTracker {
    private BlockState blockState;
    private CompoundTag compoundTag;
    private BlockPos blockPos;
    private int ticksLeft;

    public BlockTracker(BlockState blockState, @Nullable CompoundTag compoundTag, BlockPos blockPos, int ticksLeft) {
        this.blockState = blockState;
        this.compoundTag = compoundTag;
        this.blockPos = blockPos.immutable();
        this.ticksLeft = ticksLeft;
    }

    public BlockState getBlockState() {
        return blockState;
    }

    public BlockPos getBlockPos() {
        return blockPos;
    }

    public int getTicksLeft() {
        return ticksLeft;
    }

    public void setTicksLeft(int ticksLeft) {
        this.ticksLeft = ticksLeft;
    }

    public CompoundTag getCompoundTag() {
        return compoundTag;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BlockTracker that = (BlockTracker) o;
        return blockPos.equals(that.blockPos);
    }

    @Override
    public int hashCode() {
        return Objects.hash(blockPos);
    }
}
