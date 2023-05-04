package minmaximilian.pvp_enhancements.regen.util;

import net.minecraft.core.Registry;
import net.minecraft.world.level.block.state.BlockState;

public class LegalPlacements {
    public static boolean filterBlocks(BlockState blockState) {
        switch (Registry.BLOCK.getKey(blockState.getBlock()).toString()) {
            case "minecraft:air", "minecraft:void_air", "minecraft:cave_air", "minecraft:fire":
                return false;
        }
        return true;
    }
}
