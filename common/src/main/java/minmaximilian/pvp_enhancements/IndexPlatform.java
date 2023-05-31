package minmaximilian.pvp_enhancements;

import dev.architectury.injectables.annotations.ExpectPlatform;

public class IndexPlatform {
    @ExpectPlatform
    public static int getModGroupId() {
        throw new AssertionError();
    }

    @ExpectPlatform
    public static boolean isModLoaded(String id) {
        throw new AssertionError();
    }
}
