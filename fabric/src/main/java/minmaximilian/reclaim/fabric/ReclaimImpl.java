package minmaximilian.reclaim.fabric;


import static minmaximilian.reclaim.Reclaim.REGISTRATE;

import fuzs.forgeconfigapiport.api.config.v2.ForgeConfigRegistry;
import fuzs.forgeconfigapiport.api.config.v2.ModConfigEvents;
import minmaximilian.reclaim.Reclaim;
import minmaximilian.reclaim.config.ReclaimConfig;
import net.fabricmc.api.ModInitializer;

public class ReclaimImpl implements ModInitializer {

    public static void finalizeRegistrate() {
        REGISTRATE.register();
    }

    @Override
    public void onInitialize() {
        Reclaim.init();

        ReclaimConfig.registerConfigs(
            (t, c) -> ForgeConfigRegistry.INSTANCE.register(Reclaim.MOD_ID, t, c));

        ModConfigEvents.loading(Reclaim.MOD_ID).register(ReclaimConfig::onLoad);
        ModConfigEvents.reloading(Reclaim.MOD_ID).register(ReclaimConfig::onReload);

        ReclaimCommonEventsImpl.register();
    }
}
