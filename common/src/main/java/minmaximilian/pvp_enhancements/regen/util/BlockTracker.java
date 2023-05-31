package minmaximilian.pvp_enhancements.regen.util;

import java.util.Comparator;
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
        this.blockPos = blockPos;
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

    public static class BlockTrackerComparator implements Comparator<BlockTracker> {
        @Override
        public int compare(BlockTracker b1, BlockTracker b2) {
            BlockPos bPos1 = b1.getBlockPos();
            BlockPos bPos2 = b2.getBlockPos();

            int yCompare = Integer.compare(bPos1.getY(), bPos2.getY());
            if (yCompare != 0) return yCompare;

            int xCompare = Integer.compare(bPos1.getX(), bPos2.getX());
            if (xCompare != 0) return xCompare;

            return Integer.compare(bPos1.getZ(), bPos2.getZ());
        }
    }
}
