package minmaximilian.pvp_enhancements;

import minmaximilian.pvp_enhancements.compat.Mods;
import minmaximilian.pvp_enhancements.ponder.PonderIndex;

public class PvPEnhancementsClient {
    public static void init() {
        Mods.CREATE.executeIfInstalled(() -> PonderIndex::register);
    }
}
