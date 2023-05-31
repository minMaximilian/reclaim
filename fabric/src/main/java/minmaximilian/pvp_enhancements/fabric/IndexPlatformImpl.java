package minmaximilian.pvp_enhancements.fabric;

import io.github.fabricators_of_create.porting_lib.util.ItemGroupUtil;
import net.fabricmc.loader.api.FabricLoader;

public class IndexPlatformImpl {
    public static int getModGroupId() {
        return ItemGroupUtil.expandArrayAndGetId();
    }

    public static boolean isModLoaded(String id) {
        return FabricLoader.getInstance().isModLoaded(id);
    }
}
