package minmaximilian.pvp_enhancements.compat.handlers;

import minmaximilian.pvp_enhancements.regen.ChunkData;
import minmaximilian.pvp_enhancements.regen.util.BlockTracker;
import minmaximilian.pvp_enhancements.regen.util.LegalPlacements;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

public class FireHandler {

    public static void handleFire(Level level, BlockPos blockPos) {
        handleFire(level, blockPos, level.getBlockState(blockPos));
    }

    public static void handleFire(Level level, BlockPos blockPos, BlockState blockState) {
        if (!LegalPlacements.filterBlock(blockState)) {
            return;
        }
        ResourceLocation resourceLocation = level.dimension().location();
        BlockTracker blockTracker = new BlockTracker(blockState, null, blockPos);
        ChunkData.upsertSingle(resourceLocation, new ChunkPos(blockPos), blockTracker);
    }
}
