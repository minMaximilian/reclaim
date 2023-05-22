package minmaximilian.pvp_enhancements.regen.util;

import net.minecraft.core.Registry;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.block.state.BlockState;

public class LegalPlacements {
    public static boolean filterBlocks(BlockState blockState) {
        return filterBlocksInner(blockState);
    }

    private static boolean filterBlocksInner(BlockState blockState) {
        switch (Registry.BLOCK.getKey(blockState.getBlock())
            .toString()) {
            case "minecraft:air", "minecraft:void_air", "minecraft:cave_air", "minecraft:fire", "minecraft:water", "minecraft:lava":
                return false;
        }
        return true;
    }

    public static boolean filterBlocks(BlockState blockState, Explosion explosion) {
        if (!blockState.getBlock()
            .dropFromExplosion(explosion)) return false;
        return filterBlocksInner(blockState);
    }
}
