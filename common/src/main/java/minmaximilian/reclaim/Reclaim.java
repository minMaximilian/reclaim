package minmaximilian.reclaim;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import dev.architectury.injectables.annotations.ExpectPlatform;
import minmaximilian.reclaim.data.PvPRegistrate;
import minmaximilian.reclaim.regen.SavedChunkDataManager;
import net.minecraft.resources.ResourceLocation;

public class Reclaim {

    public static final Logger LOGGER = LogManager.getLogger();
    public static final String MOD_ID = "reclaim";
    public static final PvPRegistrate REGISTRATE = IndexPlatform.createRegistrate(MOD_ID);
    public static final SavedChunkDataManager SAVED_CHUNKS = new SavedChunkDataManager();

    public static void init() {
        IndexPlatform.register();
        finalizeRegistrate();
    }

    @ExpectPlatform
    public static void finalizeRegistrate() {
        throw new AssertionError();
    }

    public static ResourceLocation asResource(String name) {
        return new ResourceLocation(MOD_ID, name);
    }
}
