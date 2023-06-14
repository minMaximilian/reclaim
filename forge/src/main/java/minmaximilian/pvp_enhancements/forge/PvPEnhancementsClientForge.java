package minmaximilian.pvp_enhancements.forge;

import minmaximilian.pvp_enhancements.PvPEnhancementsClient;
import net.minecraftforge.eventbus.api.IEventBus;

public class PvPEnhancementsClientForge {
    public static void prepareClient(IEventBus modEventBus, IEventBus forgeEventBus) {
        PvPEnhancementsClient.init();
    }
}
