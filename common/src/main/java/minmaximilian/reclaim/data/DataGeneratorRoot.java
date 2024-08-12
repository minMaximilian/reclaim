package minmaximilian.reclaim.data;

import com.simibubi.create.foundation.ponder.PonderLocalization;

import minmaximilian.reclaim.Reclaim;
import minmaximilian.reclaim.compat.emi.EmiRecipeDefaultsGen;
import minmaximilian.reclaim.compat.ponder.PonderIndex;
import minmaximilian.reclaim.data.providers.BlockItemsRecipeProvider;
import net.minecraft.data.DataGenerator;

public class DataGeneratorRoot {

    public static void gatherData(DataGenerator.PackGenerator gen) {
        gen.addProvider(BlockItemsRecipeProvider::new);
        gen.addProvider(EmiRecipeDefaultsGen::new);
        PonderIndex.register();
        PonderLocalization.provideRegistrateLang(Reclaim.REGISTRATE);
    }
}
