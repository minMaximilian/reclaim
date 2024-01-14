package minmaximilian.pvp_enhancements.data;

import com.simibubi.create.foundation.ponder.PonderLocalization;

import minmaximilian.pvp_enhancements.PvPEnhancements;
import minmaximilian.pvp_enhancements.compat.emi.EmiRecipeDefaultsGen;
import minmaximilian.pvp_enhancements.compat.ponder.PonderIndex;
import minmaximilian.pvp_enhancements.data.providers.BlockItemsRecipeProvider;
import net.minecraft.data.DataGenerator;

public class DataGeneratorRoot {

    public static void gatherData(DataGenerator.PackGenerator gen) {
        gen.addProvider(BlockItemsRecipeProvider::new);
        gen.addProvider(EmiRecipeDefaultsGen::new);
        PonderIndex.register();
        PonderLocalization.provideRegistrateLang(PvPEnhancements.REGISTRATE);
    }
}
