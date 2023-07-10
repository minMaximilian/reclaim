package minmaximilian.pvp_enhancements.regen;


import static minmaximilian.pvp_enhancements.PvPEnhancements.SAVED_CHUNKS;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import minmaximilian.pvp_enhancements.regen.util.BlockTracker;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.ChunkPos;

public class ChunkData {

    private static final Map<ResourceLocation, Map<ChunkPos, ChunkTracker>> chunkData = new ConcurrentHashMap<>();

    public static Map<ResourceLocation, Map<ChunkPos, ChunkTracker>> getChunkTrackers() {
        return chunkData;
    }

    public static void upsertExplosion(ResourceLocation resourceLocation,
        Map<ChunkPos, List<BlockTracker>> needHealing) {
        upsertResourceLocation(resourceLocation);
        for (Map.Entry<ChunkPos, List<BlockTracker>> entry : needHealing.entrySet()) {
            upsertChunk(resourceLocation, entry.getKey(), entry.getValue());
        }
    }

    public static void upsertResourceLocation(ResourceLocation resourceLocation) {
        if (containsResourceLocation(resourceLocation)) {
            return;
        }
        chunkData.put(resourceLocation, new ConcurrentHashMap<>());
    }

    public static void upsertSingle(ResourceLocation resourceLocation, ChunkPos chunkPos, BlockTracker blockTracker) {
        upsertResourceLocation(resourceLocation);

        Map<ChunkPos, ChunkTracker> resourceChunks = chunkData.computeIfAbsent(resourceLocation,
            k -> new ConcurrentHashMap<>());
        ChunkTracker chunkTracker = resourceChunks.computeIfAbsent(chunkPos, k -> new ChunkTracker());

        chunkTracker.put(blockTracker);

        ActiveChunks.upsertChunk(resourceLocation, chunkPos);
        SAVED_CHUNKS.markDataDirty();
    }

    public static void upsertChunk(ResourceLocation resourceLocation, ChunkPos chunkPos,
        List<BlockTracker> blockTrackerList) {
        upsertResourceLocation(resourceLocation);

        Map<ChunkPos, ChunkTracker> resourceChunks = chunkData.computeIfAbsent(resourceLocation,
            k -> new ConcurrentHashMap<>());
        ChunkTracker chunkTracker = resourceChunks.getOrDefault(chunkPos, new ChunkTracker());
        chunkTracker.putAll(blockTrackerList);
        resourceChunks.put(chunkPos, chunkTracker);

        ActiveChunks.upsertChunk(resourceLocation, chunkPos);
        SAVED_CHUNKS.markDataDirty();
    }

    public static boolean containsResourceLocation(ResourceLocation resourceLocation) {
        return chunkData.containsKey(resourceLocation);
    }

    public static boolean containsChunk(ResourceLocation resourceLocation, ChunkPos chunkPos) {
        return chunkData.containsKey(resourceLocation) && chunkData.get(resourceLocation)
            .containsKey(chunkPos);
    }

    public static void removeChunk(ResourceLocation resourceLocation, ChunkPos pos) {
        getResourceLocation(resourceLocation).remove(pos);
        ActiveChunks.removeChunk(resourceLocation, pos);
        SAVED_CHUNKS.markDataDirty();
    }

    public static Map<ChunkPos, ChunkTracker> getResourceLocation(ResourceLocation resourceLocation) {
        return chunkData.get(resourceLocation);
    }

    public static ChunkTracker getChunkTracker(ResourceLocation resourceLocation, ChunkPos chunkPos) {
        if (getResourceLocation(resourceLocation) != null) {
            return getResourceLocation(resourceLocation).get(chunkPos);
        }
        return null;
    }
}
