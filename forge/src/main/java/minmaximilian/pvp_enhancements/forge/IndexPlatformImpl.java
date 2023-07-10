package minmaximilian.pvp_enhancements.forge;

import minmaximilian.pvp_enhancements.data.PvPRegistrate;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModList;

public class IndexPlatformImpl {

    public static int getModGroupId() {
        return -1;
    }

    public static boolean isModLoaded(String id) {
        return ModList.get().isLoaded(id);
    }

    public static PvPRegistrate createRegistrate(String modid) {
        return new PvPRegistrate(modid) {
            @Override
            public PvPRegistrate registerEventListeners(Object bus) {
                return super.registerEventListeners((IEventBus) bus);
            }
        };
    }
}
