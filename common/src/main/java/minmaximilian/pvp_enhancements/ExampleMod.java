package minmaximilian.pvp_enhancements;

public class ExampleMod {
    public static final String MOD_ID = "maxs_pvp_enhancements";
    public static void init() {
        System.out.println(ExampleExpectPlatform.getConfigDirectory().toAbsolutePath().normalize().toString());
    }
}
