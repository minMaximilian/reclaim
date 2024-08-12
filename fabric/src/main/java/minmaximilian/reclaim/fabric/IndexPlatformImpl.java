package minmaximilian.reclaim.fabric;

import minmaximilian.reclaim.data.PvPRegistrate;
import net.fabricmc.loader.api.FabricLoader;

public class IndexPlatformImpl {

    public static boolean isModLoaded(String id) {
        return FabricLoader.getInstance().isModLoaded(id);
    }

    public static PvPRegistrate createRegistrate(String modid) {
        return new PvPRegistrate(modid);
    }
}
