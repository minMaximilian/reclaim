package minmaximilian.reclaim;

import minmaximilian.reclaim.compat.Mods;
import minmaximilian.reclaim.compat.ponder.PonderIndex;

public class ReclaimClient {

    public static void init() {
        Mods.CREATE.executeIfInstalled(() -> PonderIndex::register);
    }
}
