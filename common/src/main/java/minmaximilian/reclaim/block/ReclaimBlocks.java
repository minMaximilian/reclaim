package minmaximilian.reclaim.block;

import static minmaximilian.reclaim.Reclaim.REGISTRATE;

import com.tterrag.registrate.builders.BlockBuilder;
import com.tterrag.registrate.util.entry.BlockEntry;
import com.tterrag.registrate.util.nullness.NonNullUnaryOperator;

import minmaximilian.reclaim.IndexPlatform;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SoundType;

public class ReclaimBlocks {

    public static final BlockEntry<WallPlaster> WALL_PLASTER = REGISTRATE
        .block("wall_plaster", WallPlaster::new)
        .properties(p -> p.sound(SoundType.GRAVEL))
        .transform(shovel())
        .lang("Wall Plaster")
        .simpleItem()
        .register();

    static {
        IndexPlatform.useBaseTab();
    }

    @SuppressWarnings("EmptyMethod")
    public static void register() {
    }

    private static <T extends Block, P> NonNullUnaryOperator<BlockBuilder<T, P>> shovel() {
        return b -> b.initialProperties(() -> Blocks.SAND)
            .tag(BlockTags.MINEABLE_WITH_SHOVEL);
    }
}
