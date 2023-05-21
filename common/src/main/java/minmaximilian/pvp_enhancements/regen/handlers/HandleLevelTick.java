package minmaximilian.pvp_enhancements.regen.handlers;

import static minmaximilian.pvp_enhancements.regen.util.LegalPlacements.filterBlocks;

import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import minmaximilian.pvp_enhancements.regen.ActiveChunks;
import minmaximilian.pvp_enhancements.regen.ChunkData;
import minmaximilian.pvp_enhancements.regen.util.BlockTracker;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntity;

public class HandleLevelTick {
    public static void handleLevelTick(Level level) {
        if (!ActiveChunks.containsResourceLocation(level.dimension()
            .location())) return;
        Map<ChunkPos, List<BlockTracker>> dimensionChunks = ChunkData.getResourceLocation(level.dimension()
            .location());
        for (Map.Entry<ChunkPos, List<BlockTracker>> entry : dimensionChunks.entrySet()) {
            if (ActiveChunks.containsChunk(level.dimension()
                .location(), entry.getKey()) && handleChunkList(level, entry.getValue()))
                ChunkData.removeChunk(level.dimension()
                    .location(), entry.getKey());
        }
    }

    private static boolean handleChunkList(Level level, List<BlockTracker> blockTrackerList) {
        blockTrackerList.forEach(blockTracker -> {
            switch (blockTracker.getTicksLeft()) {
                case 0:
                    healBlockTracker(level, blockTracker);
                    blockTracker.setTicksLeft(blockTracker.getTicksLeft() - 1);
                    break;
                case -1:
                    break;
                default:
                    blockTracker.setTicksLeft(blockTracker.getTicksLeft() - 1);
            }
        });
        return blockTrackerList.get(blockTrackerList.size() - 1)
            .getTicksLeft() == -1;
    }

    private static void healBlockTracker(Level level, BlockTracker blockTracker) {
        if (filterBlocks(level.getBlockState(blockTracker.getBlockPos()))) return;

        if (blockTracker.getBlockState()
            .getBlock()
            .equals(Blocks.NETHER_PORTAL))
            level.setBlock(blockTracker.getBlockPos(), Blocks.FIRE.defaultBlockState(), 2);
        else level.setBlock(blockTracker.getBlockPos(), blockTracker.getBlockState(), 2);

        if (blockTracker.getCompoundTag() != null)
            level.setBlockEntity(BlockEntity.loadStatic(blockTracker.getBlockPos(), blockTracker.getBlockState(), blockTracker.getCompoundTag()));
        level.playSound(null, blockTracker.getBlockPos()
            .getX(), blockTracker.getBlockPos()
            .getY(), blockTracker.getBlockPos()
            .getZ(), SoundEvents.BASALT_PLACE, SoundSource.BLOCKS, 0.8F, 0.9F);
    }

    public static void healChunks() {
        Map<ResourceLocation, Map<ChunkPos, List<BlockTracker>>> damagedBlocks = ChunkData.getDamagedBlocks();
        AtomicInteger i = new AtomicInteger(1);
        for (Map.Entry<ResourceLocation, Map<ChunkPos, List<BlockTracker>>> locationMapEntry : damagedBlocks.entrySet()) {
            for (Map.Entry<ChunkPos, List<BlockTracker>> chunkPosListEntry : locationMapEntry.getValue()
                .entrySet()) {
                if (ActiveChunks.containsChunk(locationMapEntry.getKey(), chunkPosListEntry.getKey()))
                    chunkPosListEntry.getValue()
                        .forEach(blockTracker -> blockTracker.setTicksLeft(i.getAndIncrement()));
            }
        }
    }
}
