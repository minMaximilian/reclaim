package minmaximilian.pvp_enhancements.data.providers.fabric;

import java.util.function.Consumer;

import minmaximilian.pvp_enhancements.data.providers.BlockItemsRecipeProvider;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeProvider;

public class BlockItemsRecipeProviderImpl extends BlockItemsRecipeProvider {

    protected BlockItemsRecipeProviderImpl(DataGenerator pGenerator) {
        super(pGenerator);
    }

    public static RecipeProvider create(DataGenerator gen) {
        BlockItemsRecipeProviderImpl provider = new BlockItemsRecipeProviderImpl(gen);
        return new FabricRecipeProvider((FabricDataGenerator) gen) {
            protected void generateRecipes(Consumer<FinishedRecipe> exporter) {
                provider.registerRecipes(exporter);
            }
        };
    }
}
