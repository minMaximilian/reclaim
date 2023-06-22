package minmaximilian.pvp_enhancements.compat;

import minmaximilian.pvp_enhancements.PvPEnhancementsCommonEvents;
import rbasamoyai.createbigcannons.forge.events.OnCannonBreakBlockImpl;

public class PvPEnhancementsCBCEvents {
    public static void onPenetration(OnCannonBreakBlockImpl onCannonBreakBlock) {
        PvPEnhancementsCommonEvents.handlePenetration(onCannonBreakBlock);
    }
}
