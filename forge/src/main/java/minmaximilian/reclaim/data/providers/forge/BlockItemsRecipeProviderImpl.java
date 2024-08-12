package minmaximilian.reclaim.data.providers.forge;

import java.util.function.Consumer;

import org.jetbrains.annotations.NotNull;

import minmaximilian.reclaim.data.providers.BlockItemsRecipeProvider;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeProvider;

public class BlockItemsRecipeProviderImpl extends BlockItemsRecipeProvider {

    protected BlockItemsRecipeProviderImpl(PackOutput pPackoutput) {
        super(pPackoutput);
    }

    public static RecipeProvider create(PackOutput gen) {
        BlockItemsRecipeProviderImpl provider = new BlockItemsRecipeProviderImpl(gen);
        return new RecipeProvider(gen) {
            @Override
            protected void buildRecipes(@NotNull Consumer<FinishedRecipe> writer) {
                provider.buildRecipes(writer);
            }
        };
    }
}
