package minmaximilian.pvp_enhancements.regen.util;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.state.BlockState;

import javax.annotation.Nullable;
import java.util.Comparator;

public class BlockTracker {
    private ResourceLocation resourceLocation;
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

    public static class BlockTrackerComparator implements Comparator<BlockTracker> {
        @Override
        public int compare(BlockTracker b1, BlockTracker b2) {
            BlockPos bPos1 = b1.getBlockPos();
            BlockPos bPos2 = b2.getBlockPos();

            return Integer.compare(bPos1.getY(), bPos2.getY());
        }
    }
}
