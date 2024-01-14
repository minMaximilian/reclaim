package minmaximilian.pvp_enhancements.data.providers;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import org.jetbrains.annotations.NotNull;

import minmaximilian.pvp_enhancements.PvPEnhancements;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeProvider;

public abstract class PvPRecipeProvider extends RecipeProvider {

    protected final List<GeneratedRecipe> all = new ArrayList<>();

    public PvPRecipeProvider(PackOutput pOutput) {
        super(pOutput);
    }


    @Override
    public void buildRecipes(@NotNull Consumer<FinishedRecipe> p_200404_1_) {
        all.forEach(c -> c.register(p_200404_1_));
        PvPEnhancements.LOGGER.info(getName() + " registered " + all.size() + " recipe" + (all.size() == 1 ? "" : "s"));
    }

    protected GeneratedRecipe register(GeneratedRecipe recipe) {
        all.add(recipe);
        return recipe;
    }

    @FunctionalInterface
    public interface GeneratedRecipe {
        void register(Consumer<FinishedRecipe> consumer);
    }
}
