package minmaximilian.pvp_enhancements.fabric.data;

import minmaximilian.pvp_enhancements.data.PvPRegistrate;

public class PvPRegistrateFabric extends PvPRegistrate {
    protected PvPRegistrateFabric(String modid) {
        super(modid);
    }

    public static PvPRegistrate create(String modid) {
        return new PvPRegistrateFabric(modid);
    }
}
