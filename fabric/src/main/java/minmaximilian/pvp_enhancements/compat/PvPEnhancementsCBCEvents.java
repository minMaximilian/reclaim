package minmaximilian.pvp_enhancements.compat;

import minmaximilian.pvp_enhancements.PvPEnhancementsCommonEvents;
import rbasamoyai.createbigcannons.fabric.events.OnCannonBreakBlockEvent;
import rbasamoyai.createbigcannons.multiloader.event_classes.OnCannonBreakBlock;

public class PvPEnhancementsCBCEvents {
    public static void register() {
        OnCannonBreakBlockEvent.EVENT.register(PvPEnhancementsCBCEvents::onPenetration);
    }

    public static void onPenetration(OnCannonBreakBlock onCannonBreakBlock) {
        PvPEnhancementsCommonEvents.handlePenetration(onCannonBreakBlock);
    }
}
