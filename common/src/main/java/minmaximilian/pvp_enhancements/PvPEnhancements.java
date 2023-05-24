package minmaximilian.pvp_enhancements;

import com.simibubi.create.foundation.data.CreateRegistrate;

import minmaximilian.pvp_enhancements.base.PvPEnhancementsCreativeModeTab;
import minmaximilian.pvp_enhancements.item.Items;
import minmaximilian.pvp_enhancements.ponder.PonderIndex;
import minmaximilian.pvp_enhancements.regen.SavedChunkDataManager;

public class PvPEnhancements {
    public static final String MOD_ID = "maxs_pvp_enhancements";

    public static final CreateRegistrate REGISTRATE = CreateRegistrate.create(MOD_ID);

    public static final SavedChunkDataManager SAVED_CHUNKS = new SavedChunkDataManager();

    public static void init() {
        PvPEnhancementsCreativeModeTab.register();
        Items.register();
        PonderIndex.register();
    }
}
