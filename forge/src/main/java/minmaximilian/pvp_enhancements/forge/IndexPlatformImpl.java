package minmaximilian.pvp_enhancements.forge;

import net.minecraftforge.fml.ModList;

public class IndexPlatformImpl {
    public static int getModGroupId() {
        return -1;
    }

    public static boolean isModLoaded(String id) {
        return ModList.get().isLoaded(id);
    }
}
