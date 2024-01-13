package minmaximilian.pvp_enhancements.data.providers;

import com.tterrag.registrate.util.entry.ItemProviderEntry;

import dev.architectury.injectables.annotations.ExpectPlatform;

import java.util.function.Supplier;

import minmaximilian.pvp_enhancements.block.PvPEnhancementsBlocks;
import minmaximilian.pvp_enhancements.item.PvPEnhancementsItems;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Blocks;

@SuppressWarnings("unused")
public class BlockItemsRecipeProvider extends PvPRecipeProvider {

    GeneratedRecipe HEPHAESTUS_BAG = create(PvPEnhancementsItems.HEPHAESTUS_BAG)
        .unlockedBy(() -> Items.NETHER_STAR)
        .viaShaped(b -> b.define('+', Items.NETHER_STAR)
            .define('#', () -> Items.ECHO_SHARD)
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

    GeneratedRecipeBuilder create(Supplier<ItemLike> result) {
        return new GeneratedRecipeBuilder("/", result);
    }

    GeneratedRecipeBuilder create(ResourceLocation result) {
        return new GeneratedRecipeBuilder("/", result);
    }

    GeneratedRecipeBuilder create(ItemProviderEntry<? extends ItemLike> result) {
        return create(result::get);
    }

    public BlockItemsRecipeProvider(PackOutput pOutput) {
        super(pOutput);
    }
}
