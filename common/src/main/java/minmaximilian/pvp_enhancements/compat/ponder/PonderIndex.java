package minmaximilian.pvp_enhancements.compat.ponder;

import com.simibubi.create.foundation.ponder.PonderRegistrationHelper;

import minmaximilian.pvp_enhancements.PvPEnhancements;
import minmaximilian.pvp_enhancements.item.PvPEnhancementsItems;

public class PonderIndex {
    private static final PonderRegistrationHelper HELPER = new PonderRegistrationHelper(PvPEnhancements.MOD_ID);

    public static void register() {
        HELPER.forComponents(PvPEnhancementsItems.HEPHAESTUS_BAG)
            .addStoryBoard("items/hephaestus_bag", ItemScenes::chargingHephaestusBag);
    }
}
