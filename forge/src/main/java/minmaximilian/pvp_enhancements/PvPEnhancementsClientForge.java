package minmaximilian.pvp_enhancements;

import net.minecraftforge.eventbus.api.IEventBus;

public class PvPEnhancementsClientForge {
    public static void prepareClient(IEventBus modEventBus, IEventBus forgeEventBus) {
        PvPEnhancementsClient.init();
    }
}
