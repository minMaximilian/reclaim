package minmaximilian.pvp_enhancements.regen;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import minmaximilian.pvp_enhancements.regen.util.BlockTracker;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.ChunkPos;

public class ActiveChunkData {
    private static ConcurrentHashMap<ResourceLocation, Map<ChunkPos, List<BlockTracker>>> unhealedBlocks = new ConcurrentHashMap<>();

    public static ConcurrentHashMap<ResourceLocation, Map<ChunkPos, List<BlockTracker>>> getDamagedBlocks() {
        return unhealedBlocks;
    }

    public static void setDamagedBlocks(ConcurrentHashMap<ResourceLocation, Map<ChunkPos, List<BlockTracker>>> damagedBlocks) {
        unhealedBlocks = damagedBlocks;
    }

    public static boolean containsResourceLocation(ResourceLocation resourceLocation) {
        return unhealedBlocks.containsKey(resourceLocation);
    }

    public static boolean containsChunk(ResourceLocation resourceLocation, ChunkPos chunkPos) {
        return unhealedBlocks.containsKey(resourceLocation) && unhealedBlocks.get(resourceLocation).containsKey(chunkPos);
    }

    public static Map<ChunkPos, List<BlockTracker>> getResourceLocation(ResourceLocation resourceLocation) {
        return unhealedBlocks.get(resourceLocation);
    }

    public static List<BlockTracker> getChunk(ResourceLocation resourceLocation, ChunkPos chunkPos) {
        return unhealedBlocks.get(resourceLocation).get(chunkPos);
    }

    public static void upsertResourceLocation(ResourceLocation resourceLocation) {
        if (containsResourceLocation(resourceLocation)) return;
        unhealedBlocks.put(resourceLocation, new ConcurrentHashMap<>());
    }

    public static void upsertChunk(ResourceLocation resourceLocation, ChunkPos chunkPos, List<BlockTracker> blockTrackerList) {
        upsertResourceLocation(resourceLocation);
        if (containsChunk(resourceLocation, chunkPos)) {
            unhealedBlocks.get(resourceLocation).get(chunkPos).addAll(blockTrackerList);
        } else {
            unhealedBlocks.get(resourceLocation).put(chunkPos, blockTrackerList);
        }
    }

    public static void upsertExplosion(ResourceLocation resourceLocation, Map<ChunkPos, List<BlockTracker>> needHealing) {
        upsertResourceLocation(resourceLocation);
        for (Map.Entry<ChunkPos, List<BlockTracker>> entry : needHealing.entrySet()) {
            upsertChunk(resourceLocation, entry.getKey(), entry.getValue());
        }
    }

    public static void removeChunk(ResourceLocation resourceLocation, ChunkPos pos) {
        getResourceLocation(resourceLocation).remove(pos);
    }

}
