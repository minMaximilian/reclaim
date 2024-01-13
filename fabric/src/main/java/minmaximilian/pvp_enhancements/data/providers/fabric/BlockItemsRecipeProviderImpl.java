package minmaximilian.pvp_enhancements.data.providers.fabric;

import java.util.function.Consumer;

import minmaximilian.pvp_enhancements.data.providers.BlockItemsRecipeProvider;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeProvider;

public class BlockItemsRecipeProviderImpl extends BlockItemsRecipeProvider {

    protected BlockItemsRecipeProviderImpl(PackOutput packOutput) {
        super(packOutput);
    }

    public static RecipeProvider create(PackOutput packOutput) {
        BlockItemsRecipeProviderImpl provider = new BlockItemsRecipeProviderImpl(packOutput);
        return new FabricRecipeProvider((FabricDataOutput) packOutput) {
            @Override
            public void buildRecipes(Consumer<FinishedRecipe> exporter) {
                provider.registerRecipes(exporter);
            }
        };
    }
}
