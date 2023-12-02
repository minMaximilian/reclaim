package minmaximilian.pvp_enhancements.fabric;


import static minmaximilian.pvp_enhancements.PvPEnhancements.REGISTRATE;

import fuzs.forgeconfigapiport.api.config.v2.ForgeConfigRegistry;
import fuzs.forgeconfigapiport.api.config.v2.ModConfigEvents;
import minmaximilian.pvp_enhancements.PvPEnhancements;
import minmaximilian.pvp_enhancements.config.PvPEnhancementsConfig;
import net.fabricmc.api.ModInitializer;

public class PvPEnhancementsImpl implements ModInitializer {

    public static void finalizeRegistrate() {
        REGISTRATE.register();
    }

    @Override
    public void onInitialize() {
        PvPEnhancements.init();

        PvPEnhancementsConfig.registerConfigs(
            (t, c) -> ForgeConfigRegistry.INSTANCE.register(PvPEnhancements.MOD_ID, t, c));

        ModConfigEvents.loading(PvPEnhancements.MOD_ID).register(PvPEnhancementsConfig::onLoad);
        ModConfigEvents.reloading(PvPEnhancements.MOD_ID).register(PvPEnhancementsConfig::onReload);

        PvPEnhancementsCommonEventsImpl.register();
    }
}
