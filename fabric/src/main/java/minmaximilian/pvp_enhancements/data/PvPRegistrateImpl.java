package minmaximilian.pvp_enhancements.data;

public class PvPRegistrateImpl extends PvPRegistrate {
    protected PvPRegistrateImpl(String modid) {
        super(modid);
    }

    public static PvPRegistrate create(String modid) {
        return new PvPRegistrateImpl(modid);
    }
}
