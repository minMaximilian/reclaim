package minmaximilian.pvp_enhancements.regen.handlers;

import minmaximilian.pvp_enhancements.regen.ActiveChunks;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.chunk.ChunkAccess;

public class HandleChunkLoading {

    public static void handleChunkUnloading(LevelAccessor level, ChunkAccess chunk) {
        ResourceLocation resourceLocation = level.dimensionType()
            .effectsLocation();
        if (ActiveChunks.containsChunk(resourceLocation, chunk.getPos())) {
            ActiveChunks.removeChunk(resourceLocation, chunk.getPos());
        }
    }

    public static void handleChunkLoading(LevelAccessor level, ChunkAccess chunk) {
        ResourceLocation resourceLocation = level.dimensionType()
            .effectsLocation();
        ActiveChunks.upsertChunk(resourceLocation, chunk.getPos());
    }
}
