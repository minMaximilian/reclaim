package minmaximilian.pvp_enhancements.data.providers;

import dev.architectury.injectables.annotations.ExpectPlatform;
import minmaximilian.pvp_enhancements.block.PvPEnhancementsBlocks;
import minmaximilian.pvp_enhancements.item.PvPEnhancementsItems;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Blocks;

public abstract class BlockItemsRecipeProvider extends PvPRecipeProvider {

    GeneratedRecipe HEPHAESTUS_BAG = create(PvPEnhancementsItems.HEPHAESTUS_BAG)
        .unlockedBy(() -> Items.NETHER_STAR)
        .viaShaped(b -> b.define('+', Items.NETHER_STAR)
            .define('#', () -> Items.AMETHYST_BLOCK)
            .pattern("###")
            .pattern("#+#")
            .pattern("###")
        );

    GeneratedRecipe WALL_PLASTER = create(PvPEnhancementsBlocks.WALL_PLASTER)
        .unlockedBy(() -> Blocks.GRAVEL)
        .viaShapeless(b -> b.requires(Blocks.GRAVEL)
            .requires(Blocks.SAND)
            .requires(Blocks.CLAY)
        );

    protected BlockItemsRecipeProvider(DataGenerator generator) {
        super(generator);
    }

    @ExpectPlatform
    public static RecipeProvider create(DataGenerator gen) {
        throw new AssertionError();
    }
}
