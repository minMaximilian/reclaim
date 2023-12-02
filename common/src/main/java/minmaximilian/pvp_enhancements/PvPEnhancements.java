package minmaximilian.pvp_enhancements;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import dev.architectury.injectables.annotations.ExpectPlatform;
import minmaximilian.pvp_enhancements.data.PvPRegistrate;
import minmaximilian.pvp_enhancements.regen.SavedChunkDataManager;
import net.minecraft.resources.ResourceLocation;

public class PvPEnhancements {

    public static final Logger LOGGER = LogManager.getLogger();
    public static final String MOD_ID = "maxs_pvp_enhancements";

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
