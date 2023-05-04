package minmaximilian.pvp_enhancements.regen;

import java.util.List;
import java.util.Map;

import minmaximilian.pvp_enhancements.regen.util.BlockTracker;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.LevelAccessor;

public class SavedChunkDataManager {
    private SavedChunkData savedChunkData;
    private Map<ChunkPos, List<BlockTracker>> chunkData;

    public SavedChunkDataManager() {
    }

    public void levelLoaded(LevelAccessor level) {
        MinecraftServer server = level.getServer();
        if (server == null || server.overworld() != level)
            return;
        savedChunkData = SavedChunkData.load(server);
        chunkData = savedChunkData.getChunkData();
    }

    public void putChunk(ChunkPos chunkPos, List<BlockTracker> blockTrackerList) {
        chunkData.put(chunkPos, blockTrackerList);
        markDataDirty();
    }

    public void removeChunk(ChunkPos chunkPos) {
        chunkData.remove(chunkPos);
        markDataDirty();
    }

    public void markDataDirty() {
        if (savedChunkData != null)
            savedChunkData.setDirty();
    }
}
