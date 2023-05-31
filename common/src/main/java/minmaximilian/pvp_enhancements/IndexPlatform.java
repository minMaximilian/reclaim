package minmaximilian.pvp_enhancements;

import dev.architectury.injectables.annotations.ExpectPlatform;
import minmaximilian.pvp_enhancements.data.PvPRegistrate;

public class IndexPlatform {
    @ExpectPlatform
    public static int getModGroupId() {
        throw new AssertionError();
    }

    @ExpectPlatform
    public static boolean isModLoaded(String id) {
        throw new AssertionError();
    }

    @ExpectPlatform
    public static PvPRegistrate createRegistrate(String id) {
        throw new AssertionError();
    }
}
