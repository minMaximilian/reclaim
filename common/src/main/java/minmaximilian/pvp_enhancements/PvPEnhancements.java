package minmaximilian.pvp_enhancements;

import minmaximilian.pvp_enhancements.base.PvPEnhancementsCreativeModeTab;
import minmaximilian.pvp_enhancements.compat.Mods;
import minmaximilian.pvp_enhancements.data.PvPRegistrate;
import minmaximilian.pvp_enhancements.item.Items;
import minmaximilian.pvp_enhancements.ponder.PonderIndex;
import minmaximilian.pvp_enhancements.regen.SavedChunkDataManager;
import net.minecraftforge.fml.config.IConfigSpec;

public class PvPEnhancements {
    public static final String MOD_ID = "maxs_pvp_enhancements";

    public static final PvPRegistrate REGISTRATE = IndexPlatform.createRegistrate(MOD_ID);

    public static final SavedChunkDataManager SAVED_CHUNKS = new SavedChunkDataManager();

    public static void init() {
        PvPEnhancementsCreativeModeTab.register();

        Items.register();

        Mods.CREATE.executeIfInstalled(() -> PonderIndex::register);
    }
}
