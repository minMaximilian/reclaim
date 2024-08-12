package minmaximilian.reclaim.regen.util;

import static net.minecraft.core.registries.BuiltInRegistries.BLOCK;

import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.block.state.BlockState;

public class LegalPlacements {

    public static boolean filterBlock(BlockState blockState) {
        return filterBlocksInner(blockState);
    }

    private static boolean filterBlocksInner(BlockState blockState) {
        return switch (BLOCK.getKey(blockState.getBlock())
            .toString()) {
            case "minecraft:air", "minecraft:void_air", "minecraft:cave_air", "minecraft:fire", "minecraft:water", "minecraft:lava" ->
                false;
            default -> true;
        };
    }

    public static boolean filterBlock(BlockState blockState, Explosion explosion) {
        if (!blockState.getBlock()
            .dropFromExplosion(explosion)) {
            return false;
        }
        return filterBlocksInner(blockState);
    }
}
