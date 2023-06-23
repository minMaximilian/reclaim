package minmaximilian.pvp_enhancements.fabric;

import io.github.fabricators_of_create.porting_lib.util.ItemGroupUtil;
import minmaximilian.pvp_enhancements.data.PvPRegistrate;
import net.fabricmc.loader.api.FabricLoader;

public class IndexPlatformImpl {
    public static int getModGroupId() {
        return ItemGroupUtil.expandArrayAndGetId();
    }

    public static boolean isModLoaded(String id) {
        return FabricLoader.getInstance().isModLoaded(id);
    }

    public static PvPRegistrate createRegistrate(String modid) {
        return new PvPRegistrate(modid);
    }
}
