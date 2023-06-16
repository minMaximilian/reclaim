package minmaximilian.pvp_enhancements.compat.handlers;

import minmaximilian.pvp_enhancements.config.PvPEnhancementsConfig;
import minmaximilian.pvp_enhancements.regen.ChunkData;
import minmaximilian.pvp_enhancements.regen.util.BlockTracker;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import rbasamoyai.createbigcannons.multiloader.event_classes.OnCannonBreakBlock;

public class HandleCreateBigCannons {
    private static final int delayInTicksBeforeHealingDamage = PvPEnhancementsConfig.COMMON.delayInTicksBeforeHealingDamage.get();

    public static void handlePenetration(OnCannonBreakBlock onCannonBreakBlock) {
        BlockPos blockPos = onCannonBreakBlock.getAffectedBlockPos();
        BlockState blockState = onCannonBreakBlock.getAffectedBlockState();
        BlockEntity blockEntity = onCannonBreakBlock.getBlockEntity();

        BlockTracker blockTracker = createBlockTracker(blockPos, blockState, blockEntity);

        ChunkData.upsertPenetration(onCannonBreakBlock.getResourceLocation(), new ChunkPos(blockPos), blockTracker);
    }

    private static BlockTracker createBlockTracker(BlockPos blockPos, BlockState blockState, BlockEntity blockEntity) {
        if (blockEntity != null)
            return new BlockTracker(blockState, blockEntity.getUpdateTag(), blockPos, delayInTicksBeforeHealingDamage);
        return new BlockTracker(blockState, null, blockPos, delayInTicksBeforeHealingDamage);
    }
}
