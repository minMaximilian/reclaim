package minmaximilian.pvp_enhancements;

import minmaximilian.pvp_enhancements.base.PvPEnhancementsCreativeModeTab;
import minmaximilian.pvp_enhancements.block.PvPEnhancementsBlocks;
import minmaximilian.pvp_enhancements.data.PvPRegistrate;
import minmaximilian.pvp_enhancements.item.PvPEnhancementsItems;
import minmaximilian.pvp_enhancements.regen.SavedChunkDataManager;
import net.minecraft.resources.ResourceLocation;

public class PvPEnhancements {
    public static final String MOD_ID = "maxs_pvp_enhancements";

    public static final PvPRegistrate REGISTRATE = IndexPlatform.createRegistrate(MOD_ID);

    public static final SavedChunkDataManager SAVED_CHUNKS = new SavedChunkDataManager();

    public static void init() {
        PvPEnhancementsCreativeModeTab.register();

        PvPEnhancementsItems.register();
        PvPEnhancementsBlocks.register();
    }

    public static ResourceLocation asResource(String name) {
        return new ResourceLocation(MOD_ID, name);
    }
}
