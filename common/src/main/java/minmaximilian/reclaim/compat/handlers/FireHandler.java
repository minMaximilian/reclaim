package minmaximilian.reclaim.compat.handlers;

import minmaximilian.reclaim.regen.ChunkData;
import minmaximilian.reclaim.regen.util.BlockTracker;
import minmaximilian.reclaim.regen.util.LegalPlacements;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

public class FireHandler {

    public static void handleFire(Level level, BlockPos blockPos) {
        BlockState blockState = level.getBlockState(blockPos);
        if (!LegalPlacements.filterBlock(blockState)) {
            return;
        }
        ResourceLocation resourceLocation = level.dimension().location();
        BlockTracker blockTracker = new BlockTracker(blockState, null, blockPos);
        ChunkData.upsertSingle(resourceLocation, new ChunkPos(blockPos), blockTracker);
    }
}
