package minmaximilian.pvp_enhancements.fabric;

import minmaximilian.pvp_enhancements.ExampleMod;
import net.fabricmc.api.ModInitializer;

public class ExampleModFabric implements ModInitializer {
    @Override
    public void onInitialize() {
        ExampleMod.init();
    }
}
