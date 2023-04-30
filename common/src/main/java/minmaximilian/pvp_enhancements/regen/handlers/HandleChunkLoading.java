package minmaximilian.pvp_enhancements.regen.handlers;

import minmaximilian.pvp_enhancements.regen.ActiveChunkData;
import minmaximilian.pvp_enhancements.regen.InactiveChunkData;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.chunk.ChunkAccess;

public class HandleChunkLoading {
	public static void handleChunkUnloading(LevelAccessor level, ChunkAccess chunk) {
		ResourceLocation resourceLocation = level.dimensionType().effectsLocation();
		if (ActiveChunkData.containsChunk(resourceLocation, chunk.getPos())) {
			InactiveChunkData.upsertChunk(resourceLocation, chunk.getPos(), ActiveChunkData.getChunk(resourceLocation, chunk.getPos()));
			ActiveChunkData.removeChunk(resourceLocation, chunk.getPos());
		}
	}

	public static void handleChunkLoading(LevelAccessor level, ChunkAccess chunk) {
		ResourceLocation resourceLocation = level.dimensionType().effectsLocation();
		if (InactiveChunkData.containsChunk(resourceLocation, chunk.getPos())) {
			ActiveChunkData.upsertChunk(resourceLocation, chunk.getPos(), InactiveChunkData.getChunk(resourceLocation, chunk.getPos()));
			InactiveChunkData.removeChunk(resourceLocation, chunk.getPos());
		}
	}
}
