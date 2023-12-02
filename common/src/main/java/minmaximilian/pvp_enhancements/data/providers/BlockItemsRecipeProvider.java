package minmaximilian.pvp_enhancements.data.providers;

import minmaximilian.pvp_enhancements.block.PvPEnhancementsBlocks;
import minmaximilian.pvp_enhancements.item.PvPEnhancementsItems;
import net.minecraft.data.PackOutput;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Blocks;

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

    public BlockItemsRecipeProvider(PackOutput packOutput) {
        super(packOutput);
    }
}
