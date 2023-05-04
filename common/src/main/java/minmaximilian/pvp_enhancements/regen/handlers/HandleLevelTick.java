package minmaximilian.pvp_enhancements.regen.handlers;

import static minmaximilian.pvp_enhancements.regen.util.LegalPlacements.filterBlocks;

import java.util.List;
import java.util.Map;

import minmaximilian.pvp_enhancements.regen.ActiveChunkData;
import minmaximilian.pvp_enhancements.regen.util.BlockTracker;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntity;

public class HandleLevelTick {
    public static void handleLevelTick(Level level) {
        if (!ActiveChunkData.containsResourceLocation(level.dimension().location())) return;
        Map<ChunkPos, List<BlockTracker>> dimensionChunks = ActiveChunkData.getResourceLocation(level.dimension().location());
        for (Map.Entry<ChunkPos, List<BlockTracker>> entry : dimensionChunks.entrySet()) {
            if (handleChunkList(level, entry.getValue()))
                ActiveChunkData.removeChunk(level.dimension().location(), entry.getKey());
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
        return blockTrackerList.get(blockTrackerList.size() - 1).getTicksLeft() == -1;
    }

    private static void healBlockTracker(Level level, BlockTracker blockTracker) {
        if (filterBlocks(level.getBlockState(blockTracker.getBlockPos()))) return;

        if (blockTracker.getBlockState().getBlock().equals(Blocks.NETHER_PORTAL))
            level.setBlock(blockTracker.getBlockPos(), Blocks.FIRE.defaultBlockState(), 2);
        else
            level.setBlock(blockTracker.getBlockPos(), blockTracker.getBlockState(), 2);

        if (blockTracker.getCompoundTag() != null && blockTracker.getCompoundTag().contains("Items"))
            level.setBlockEntity(BlockEntity.loadStatic(blockTracker.getBlockPos(), blockTracker.getBlockState(), blockTracker.getCompoundTag()));
        level.playSound(null, blockTracker.getBlockPos().getX(), blockTracker.getBlockPos().getY(), blockTracker.getBlockPos().getZ(), SoundEvents.BASALT_PLACE, SoundSource.BLOCKS, 0.8F, 0.9F);
    }
}
