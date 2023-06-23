package minmaximilian.pvp_enhancements.fabric;


import minmaximilian.pvp_enhancements.PvPEnhancements;
import minmaximilian.pvp_enhancements.config.PvPEnhancementsConfig;
import net.fabricmc.api.ModInitializer;
import net.minecraftforge.api.ModLoadingContext;
import net.minecraftforge.api.fml.event.config.ModConfigEvent;

public class PvPEnhancementsImpl implements ModInitializer {
    @Override
    public void onInitialize() {
        PvPEnhancements.init();
        PvPEnhancements.REGISTRATE.register();

        PvPEnhancementsConfig.registerConfigs((t, c) -> ModLoadingContext.registerConfig(PvPEnhancements.MOD_ID, t, c));

        ModConfigEvent.LOADING.register(PvPEnhancementsConfig::onLoad);
        ModConfigEvent.RELOADING.register(PvPEnhancementsConfig::onReload);

        PvPEnhancementsCommonEventsImpl.register();
    }
}
