package minmaximilian.reclaim.regen;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

import minmaximilian.reclaim.config.ReclaimConfig;
import minmaximilian.reclaim.regen.util.BlockTracker;
import net.minecraft.core.BlockPos;

public class ChunkTracker {

    private final TreeMap<BlockPos, BlockTracker> blockTrackers = new TreeMap<>();
    private int ticksLeft = ReclaimConfig.COMMON.delayInTicksBeforeHealingDamage.get();

    public ChunkTracker() {
    }

    public ChunkTracker(List<BlockTracker> blockTrackerList) {
        putAll(blockTrackerList);
    }

    public void forceHeal() {
        ticksLeft = 0;
    }

    public boolean healChunk() {
        return ticksLeft-- <= 0;
    }

    public boolean needsHealing() {
        return blockTrackers.size() > 0;
    }

    public void putAll(List<BlockTracker> blockTrackerList) {
        ticksLeft = ReclaimConfig.COMMON.delayInTicksBeforeHealingDamage.get();
        blockTrackerList.forEach(blockTracker -> blockTrackers.put(blockTracker.getBlockPos(), blockTracker));
    }

    public int getTicksLeft() {
        return ticksLeft;
    }

    public void setTicksLeft(int newTicksLeft) {
        ticksLeft = newTicksLeft;
    }

    public List<BlockTracker> getBlockTrackers() {
        return new ArrayList<>(blockTrackers.values());
    }

    public void put(BlockTracker blockTracker) {
        ticksLeft = ReclaimConfig.COMMON.delayInTicksBeforeHealingDamage.get();
        blockTrackers.put(blockTracker.getBlockPos(), blockTracker);
    }

    public BlockTracker pop() {
        return blockTrackers.pollFirstEntry().getValue();
    }

    public BlockTracker remove(BlockPos blockPos) {
        return blockTrackers.remove(blockPos);
    }

    public BlockTracker get(BlockPos blockPos) {
        return blockTrackers.get(blockPos);
    }
}
