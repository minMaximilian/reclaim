package minmaximilian.reclaim.data.fabric;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Set;

import io.github.fabricators_of_create.porting_lib.data.ExistingFileHelper;
import minmaximilian.reclaim.Reclaim;
import minmaximilian.reclaim.data.DataGeneratorRoot;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;

public class DataGenFabric implements DataGeneratorEntrypoint {

    @Override
    public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
        Path resources = Paths.get(System.getProperty(ExistingFileHelper.EXISTING_RESOURCES));
        ExistingFileHelper helper = new ExistingFileHelper(
            Set.of(resources), Set.of("reclaim"), false, null, null
        );

        FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();

        Reclaim.REGISTRATE.setupDatagen(pack, helper);
        DataGeneratorRoot.gatherData(pack);
    }
}
