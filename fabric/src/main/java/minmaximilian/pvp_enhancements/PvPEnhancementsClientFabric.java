package minmaximilian.pvp_enhancements;

import net.fabricmc.api.ClientModInitializer;

public class PvPEnhancementsClientFabric implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        PvPEnhancementsClient.init();
    }
}
