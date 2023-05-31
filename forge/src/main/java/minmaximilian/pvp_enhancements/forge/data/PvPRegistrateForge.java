package minmaximilian.pvp_enhancements.forge.data;

import minmaximilian.pvp_enhancements.data.PvPRegistrate;
import net.minecraftforge.eventbus.api.IEventBus;

public class PvPRegistrateForge extends PvPRegistrate {
    protected PvPRegistrateForge(String modid) {
        super(modid);
    }

    public static PvPRegistrate create(String modid) {
        return new PvPRegistrateForge(modid);
    }

    @Override
    public PvPRegistrate registerEventListeners(Object bus) {
        return super.registerEventListeners((IEventBus) bus);
    }
}
