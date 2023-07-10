package minmaximilian.pvp_enhancements.regen;

import net.minecraft.server.MinecraftServer;
import net.minecraft.world.level.LevelAccessor;

public class SavedChunkDataManager {

    private SavedChunkData savedChunkData;

    public SavedChunkDataManager() {
    }

    public void levelLoaded(LevelAccessor level) {
        MinecraftServer server = level.getServer();
        if (server == null || server.overworld() != level) {
            return;
        }
        savedChunkData = SavedChunkData.load(server);
    }

    public void markDataDirty() {
        if (savedChunkData != null) {
            savedChunkData.setDirty();
        }
    }
}
