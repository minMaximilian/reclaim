package minmaximilian.pvp_enhancements.regen.handlers;

import java.util.Map;
import java.util.Set;

import minmaximilian.pvp_enhancements.regen.ActiveChunks;
import minmaximilian.pvp_enhancements.regen.ChunkData;
import minmaximilian.pvp_enhancements.regen.ChunkTracker;
import minmaximilian.pvp_enhancements.regen.HealChunk;
import minmaximilian.pvp_enhancements.regen.util.BlockTracker;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.Level;

public class HandleLevelTick {

    public static void handleLevelTick(Level level) {
        if (!ActiveChunks.containsResourceLocation(level.dimension()
            .location())) {
            return;
        }
        Map<ChunkPos, ChunkTracker> dimensionChunks = ChunkData.getResourceLocation(level.dimension()
            .location());
        for (Map.Entry<ChunkPos, ChunkTracker> entry : dimensionChunks.entrySet()) {
            if (ActiveChunks.containsChunk(level.dimension()
                .location(), entry.getKey()) && handleChunk(level, entry.getValue())) {
                ChunkData.removeChunk(level.dimension()
                    .location(), entry.getKey());
            }
        }
    }

    private static boolean handleChunk(Level level, ChunkTracker chunkTracker) {
        if (!chunkTracker.needsHealing()) {
            return true;
        }
        if (!chunkTracker.healChunk()) {
            return false;
        }
        BlockTracker blockTracker = chunkTracker.pop();
        HealChunk.healBlockTracker(level, blockTracker);

        return false;
    }

    public static void healChunks(ResourceLocation resourceLocation, Set<ChunkPos> chunkPosSet) {
        healChunksHelper(resourceLocation, chunkPosSet);
    }

    public static void healChunks() {
        healChunksHelper(null, null);
    }

    private static void healChunksHelper(ResourceLocation resourceLocation, Set<ChunkPos> chunkPosSet) {
        Map<ResourceLocation, Map<ChunkPos, ChunkTracker>> chunkTrackers = ChunkData.getChunkTrackers();
        for (Map.Entry<ResourceLocation, Map<ChunkPos, ChunkTracker>> locationMapEntry : chunkTrackers.entrySet()) {
            if (resourceLocation != null && !locationMapEntry.getKey().equals(resourceLocation)) {
                continue;
            }

            for (Map.Entry<ChunkPos, ChunkTracker> chunkTrackerEntry : locationMapEntry.getValue().entrySet()) {
                if (chunkPosSet != null && !chunkPosSet.contains(chunkTrackerEntry.getKey())) {
                    continue;
                }

                chunkTrackerEntry.getValue().forceHeal();
            }
        }
    }
}
