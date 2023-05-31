package minmaximilian.pvp_enhancements.regen.handlers;

import minmaximilian.pvp_enhancements.config.PvPEnhancementsConfig;
import minmaximilian.pvp_enhancements.regen.ChunkData;
import minmaximilian.pvp_enhancements.regen.util.BlockTracker;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.block.state.BlockState;
import rbasamoyai.createbigcannons.multiloader.event_classes.OnCannonBreakBlock;

public class HandleCreateBigCannons {
    private static final int delayInTicksBeforeHealingDamage = PvPEnhancementsConfig.COMMON.delayInTicksBeforeHealingDamage.get();

    public static void handlePenetration(OnCannonBreakBlock onCannonBreakBlock) {
        BlockPos blockPos = onCannonBreakBlock.getAffectedBlockPos();
        BlockState blockState = onCannonBreakBlock.getAffectedBlockState();

        BlockTracker blockTracker = createBlockTracker(blockPos, blockState);

        ChunkData.upsertPenetration(onCannonBreakBlock.getResourceLocation(), new ChunkPos(blockPos), blockTracker);
    }

    private static BlockTracker createBlockTracker(BlockPos blockPos, BlockState blockState) {
        return new BlockTracker(blockState, new CompoundTag(), blockPos, delayInTicksBeforeHealingDamage);
    }
}
