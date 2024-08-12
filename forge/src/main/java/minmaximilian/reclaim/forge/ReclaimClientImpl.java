package minmaximilian.reclaim.forge;

import minmaximilian.reclaim.ReclaimClient;
import net.minecraftforge.eventbus.api.IEventBus;

public class ReclaimClientImpl {

    public static void prepareClient(IEventBus modEventBus, IEventBus forgeEventBus) {
        ReclaimClient.init();
    }
}
