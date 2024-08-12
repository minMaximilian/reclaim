package minmaximilian.reclaim.forge;

import minmaximilian.reclaim.data.PvPRegistrate;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModList;

public class IndexPlatformImpl {


    public static int getModGroupId() {
        return -1;
    }

    public static boolean isModLoaded(String id) {
        return ModList.get().isLoaded(id);
    }

    public static PvPRegistrate createRegistrate(String modId) {
        return new PvPRegistrate(modId) {
            @Override
            public PvPRegistrate registerEventListeners(Object bus) {
                return super.registerEventListeners((IEventBus) bus);
            }
        };
    }

}
