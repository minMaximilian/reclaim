package minmaximilian.reclaim.compat.ponder;

import com.simibubi.create.foundation.ponder.PonderRegistrationHelper;

import minmaximilian.reclaim.Reclaim;
import minmaximilian.reclaim.item.ReclaimItems;

public class PonderIndex {

    private static final PonderRegistrationHelper HELPER = new PonderRegistrationHelper(Reclaim.MOD_ID);

    public static void register() {
        HELPER.forComponents(ReclaimItems.HEPHAESTUS_BAG)
            .addStoryBoard("items/hephaestus_bag", ItemScenes::chargingHephaestusBag);
    }
}
