package minmaximilian.reclaim.fabric;

import minmaximilian.reclaim.ReclaimClient;
import net.fabricmc.api.ClientModInitializer;

public class ReclaimClientImpl implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        ReclaimClient.init();
    }
}
