package minmaximilian.pvp_enhancements.data;

public class PvPRegistrateFabric extends PvPRegistrate {
    protected PvPRegistrateFabric(String modid) {
        super(modid);
    }

    public static PvPRegistrate create(String modid) {
        return new PvPRegistrateFabric(modid);
    }
}
