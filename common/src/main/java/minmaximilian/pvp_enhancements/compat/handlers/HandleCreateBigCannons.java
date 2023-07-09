package minmaximilian.pvp_enhancements.compat.handlers;

import minmaximilian.pvp_enhancements.regen.ChunkData;
import minmaximilian.pvp_enhancements.regen.util.BlockTracker;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;

public class HandleCreateBigCannons {

    public static void handlePenetration(Level level, BlockPos blockPos) {
        handlePenetration(level, level.getBlockState(blockPos), blockPos.immutable());
    }

    public static void handlePenetration(Level level, BlockState blockState, BlockHitResult result) {
        BlockPos blockPos = result.getBlockPos().immutable();
        handlePenetration(level, blockState, blockPos);
    }

    private static void handlePenetration(Level level, BlockState blockState, BlockPos blockPos) {
        ResourceLocation resourceLocation = level.dimension().location();
        BlockEntity blockEntity = level.getBlockEntity(blockPos);
        BlockTracker blockTracker = createBlockTracker(blockPos, blockState, blockEntity);
        ChunkData.upsertSingle(resourceLocation, new ChunkPos(blockPos), blockTracker);
    }


    private static BlockTracker createBlockTracker(BlockPos blockPos, BlockState blockState, BlockEntity blockEntity) {
        if (blockEntity != null) {
            return new BlockTracker(blockState, blockEntity.saveWithFullMetadata(), blockPos);
        }
        return new BlockTracker(blockState, null, blockPos);
    }
}

