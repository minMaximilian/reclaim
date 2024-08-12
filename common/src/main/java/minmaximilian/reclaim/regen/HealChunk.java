package minmaximilian.reclaim.regen;

import minmaximilian.reclaim.regen.util.BlockTracker;
import minmaximilian.reclaim.regen.util.LegalPlacements;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.Tag;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.material.FluidState;

public class HealChunk {

    public static void healBlockTrackerWithoutPop(Level level, BlockTracker blockTracker) {
        HealBlockTrackerCommonLogic(level, blockTracker, blockTracker.getBlockPos());
    }

    public static void healBlockTracker(Level level, BlockTracker blockTracker) {
        BlockPos blockPos = blockTracker.getBlockPos();
        FluidState fluidState = level.getFluidState(blockPos);

        if (LegalPlacements.filterBlock(level.getBlockState(blockPos)) && (fluidState.isEmpty()
            || fluidState.isSource())) {
            level.addFreshEntity(new ItemEntity(level, blockPos.getX(), blockPos.getY(), blockPos.getZ(),
                new ItemStack(level.getBlockState(blockPos)
                    .getBlock()
                    .asItem())));
            BlockEntity blockEntity = level.getBlockEntity(blockPos);
            if (blockEntity != null) {
                CompoundTag compoundTag = blockEntity.saveWithFullMetadata();
                spawnStorageContents(compoundTag, level, blockPos);
            }
        }

        HealBlockTrackerCommonLogic(level, blockTracker, blockPos);
    }

    private static void HealBlockTrackerCommonLogic(Level level, BlockTracker blockTracker, BlockPos blockPos) {
        if (blockTracker.getBlockState()
            .getBlock()
            .equals(Blocks.NETHER_PORTAL)) {
            level.setBlock(blockPos, Blocks.FIRE.defaultBlockState(), Block.UPDATE_ALL);
        } else {
            level.setBlock(blockPos, blockTracker.getBlockState(), Block.UPDATE_ALL);
        }

        if (blockTracker.getCompoundTag() != null && blockTracker.getCompoundTag()
            .getAllKeys()
            .size() != 0) {
            level.setBlockEntity(
                BlockEntity.loadStatic(blockPos, blockTracker.getBlockState(), blockTracker.getCompoundTag()));
        }
        level.playSound(null, blockPos
            .getX(), blockPos
            .getY(), blockPos
            .getZ(), SoundEvents.BASALT_PLACE, SoundSource.BLOCKS, 0.8F, 0.9F);
    }

    public static void popBlockTracker(Level level, BlockTracker blockTracker) {
        BlockPos blockPos = blockTracker.getBlockPos();
        level.addFreshEntity(new ItemEntity(level, blockPos.getX(), blockPos.getY(), blockPos.getZ(),
            new ItemStack(blockTracker.getBlockState()
                .getBlock()
                .asItem())));

        if (blockTracker.getCompoundTag() != null && blockTracker.getCompoundTag()
            .contains("Items")) {
            spawnStorageContents(blockTracker.getCompoundTag(), level, blockPos);
        }
    }

    private static void spawnStorageContents(CompoundTag compoundTag, Level level, BlockPos blockPos) {
        ListTag items = compoundTag
            .getList("Items", Tag.TAG_COMPOUND);
        if (items != null) {
            for (int i = 0; i < items.size(); i++) {
                level.addFreshEntity(new ItemEntity(level, blockPos.getX(), blockPos.getY(), blockPos.getZ(),
                    ItemStack.of(items.getCompound(i))));
            }
        }
    }
}
