package minmaximilian.pvp_enhancements.fabric;

import io.github.fabricators_of_create.porting_lib.util.ItemGroupUtil;

public class IndexPlatformImpl {
    public static int getModGroupId() {
        return ItemGroupUtil.expandArrayAndGetId();
    }
}
