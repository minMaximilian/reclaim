package minmaximilian.pvp_enhancements.regen;


import static minmaximilian.pvp_enhancements.PvPEnhancements.SAVED_CHUNKS;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

import minmaximilian.pvp_enhancements.regen.util.BlockTracker;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.ChunkPos;

public class ChunkData {
    private static Map<ResourceLocation, Map<ChunkPos, List<BlockTracker>>> chunkData = new ConcurrentHashMap<>();

    public static Map<ResourceLocation, Map<ChunkPos, List<BlockTracker>>> getDamagedBlocks() {
        return chunkData;
    }

    public static void setDamagedBlocks(Map<ResourceLocation, Map<ChunkPos, List<BlockTracker>>> damagedBlocks) {
        chunkData = damagedBlocks;
    }

    public static List<BlockTracker> getChunk(ResourceLocation resourceLocation, ChunkPos chunkPos) {
        return chunkData.get(resourceLocation)
            .get(chunkPos);
    }

    public static void upsertExplosion(ResourceLocation resourceLocation, Map<ChunkPos, List<BlockTracker>> needHealing) {
        upsertResourceLocation(resourceLocation);
        for (Map.Entry<ChunkPos, List<BlockTracker>> entry : needHealing.entrySet()) {
            upsertChunk(resourceLocation, entry.getKey(), entry.getValue());
        }
    }

    public static void upsertResourceLocation(ResourceLocation resourceLocation) {
        if (containsResourceLocation(resourceLocation)) return;
        chunkData.put(resourceLocation, new ConcurrentHashMap<>());
    }

    public static void upsertPenetration(ResourceLocation resourceLocation, ChunkPos chunkPos, BlockTracker blockTracker) {
        upsertResourceLocation(resourceLocation);

        Map<ChunkPos, List<BlockTracker>> resourceChunks = chunkData.computeIfAbsent(resourceLocation, k -> new ConcurrentHashMap<>());
        List<BlockTracker> chunkBlockTrackers = resourceChunks.computeIfAbsent(chunkPos, k -> new ArrayList<>());

        if (!chunkBlockTrackers.contains(blockTracker)) {
            chunkBlockTrackers.add(blockTracker);
        }

        ActiveChunks.upsertChunk(resourceLocation, chunkPos);
        SAVED_CHUNKS.markDataDirty();
    }

    public static void upsertChunk(ResourceLocation resourceLocation, ChunkPos chunkPos, List<BlockTracker> blockTrackerList) {
        upsertResourceLocation(resourceLocation);

        Map<ChunkPos, List<BlockTracker>> resourceChunks = chunkData.computeIfAbsent(resourceLocation, k -> new ConcurrentHashMap<>());
        List<BlockTracker> existingBlockTrackers = resourceChunks.getOrDefault(chunkPos, new ArrayList<>());
        existingBlockTrackers.addAll(blockTrackerList);
        List<BlockTracker> deduplicatedBlockTrackers = existingBlockTrackers.stream().distinct().collect(Collectors.toList());
        resourceChunks.put(chunkPos, deduplicatedBlockTrackers);

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

    public static Map<ChunkPos, List<BlockTracker>> getResourceLocation(ResourceLocation resourceLocation) {
        return chunkData.get(resourceLocation);
    }
}
