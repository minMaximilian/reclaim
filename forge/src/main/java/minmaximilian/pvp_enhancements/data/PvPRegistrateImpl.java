package minmaximilian.pvp_enhancements.data;

import net.minecraftforge.eventbus.api.IEventBus;

public class PvPRegistrateImpl extends PvPRegistrate {
    protected PvPRegistrateImpl(String modid) {
        super(modid);
    }

    public static PvPRegistrate create(String modid) {
        return new PvPRegistrateImpl(modid);
    }

    @Override
    public PvPRegistrate registerEventListeners(Object bus) {
        return super.registerEventListeners((IEventBus) bus);
    }
}
