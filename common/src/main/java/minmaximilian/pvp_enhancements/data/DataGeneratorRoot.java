package minmaximilian.pvp_enhancements.data;

import com.simibubi.create.foundation.ponder.PonderLocalization;

import minmaximilian.pvp_enhancements.PvPEnhancements;
import minmaximilian.pvp_enhancements.data.providers.BlockItemsRecipeProvider;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.data.ExistingFileHelper;

public class DataGeneratorRoot {
    public static void register(DataGenerator gen, ExistingFileHelper helper, boolean client, boolean server) {
        gen.addProvider(true, BlockItemsRecipeProvider.create(gen));
        PonderLocalization.provideRegistrateLang(PvPEnhancements.REGISTRATE);
    }
}
