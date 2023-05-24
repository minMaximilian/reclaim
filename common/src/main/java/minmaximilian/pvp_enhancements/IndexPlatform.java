package minmaximilian.pvp_enhancements;

import dev.architectury.injectables.annotations.ExpectPlatform;

public class IndexPlatform {
    @ExpectPlatform
    public static int getModGroupId() {
        throw new AssertionError();
    }
}
