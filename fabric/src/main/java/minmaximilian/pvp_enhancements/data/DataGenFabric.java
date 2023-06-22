package minmaximilian.pvp_enhancements.data;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Set;

import minmaximilian.pvp_enhancements.PvPEnhancements;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.minecraftforge.common.data.ExistingFileHelper;

public class DataGenFabric implements DataGeneratorEntrypoint {
    @Override
    public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
        Path resources = Paths.get(System.getProperty(ExistingFileHelper.EXISTING_RESOURCES));
        ExistingFileHelper helper = new ExistingFileHelper(
            Set.of(resources), Set.of("maxs_pvp_enhancements"), true, null, null
        );
        DataGeneratorRoot.register(fabricDataGenerator, helper, true, true);
        PvPEnhancements.REGISTRATE.setupDatagen(fabricDataGenerator, helper);
    }
}
