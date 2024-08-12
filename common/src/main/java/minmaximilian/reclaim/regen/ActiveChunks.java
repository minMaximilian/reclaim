package minmaximilian.reclaim.regen;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.ChunkPos;

public class ActiveChunks {

    private static final Map<ResourceLocation, Set<ChunkPos>> activeChunks = new ConcurrentHashMap<>();

    public static boolean containsChunk(ResourceLocation resourceLocation, ChunkPos pos) {
        if (activeChunks.containsKey(resourceLocation)) {
            return activeChunks.get(resourceLocation)
                .contains(pos);
        }
        return false;
    }

    public static void removeChunk(ResourceLocation resourceLocation, ChunkPos pos) {
        activeChunks.get(resourceLocation)
            .remove(pos);
    }

    public static void upsertChunk(ResourceLocation resourceLocation, ChunkPos pos) {
        if (!ChunkData.containsChunk(resourceLocation, pos)) {
            return;
        }
        if (!activeChunks.containsKey(resourceLocation)) {
            activeChunks.put(resourceLocation, new HashSet<>());
        }
        activeChunks.get(resourceLocation)
            .add(pos);
    }

    public static boolean containsResourceLocation(ResourceLocation location) {
        return activeChunks.containsKey(location);
    }
}
