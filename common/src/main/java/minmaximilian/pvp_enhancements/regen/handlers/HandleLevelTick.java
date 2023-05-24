package minmaximilian.pvp_enhancements.regen.handlers;

import static minmaximilian.pvp_enhancements.regen.util.LegalPlacements.filterBlocks;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

import minmaximilian.pvp_enhancements.regen.ActiveChunks;
import minmaximilian.pvp_enhancements.regen.ChunkData;
import minmaximilian.pvp_enhancements.regen.util.BlockTracker;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.Tag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.material.FluidState;

public class HandleLevelTick {
    public static void handleLevelTick(Level level) {
        if (!ActiveChunks.containsResourceLocation(level.dimension()
            .location())) return;
        Map<ChunkPos, List<BlockTracker>> dimensionChunks = ChunkData.getResourceLocation(level.dimension()
            .location());
        for (Map.Entry<ChunkPos, List<BlockTracker>> entry : dimensionChunks.entrySet()) {
            if (ActiveChunks.containsChunk(level.dimension()
                .location(), entry.getKey()) && handleChunk(level, entry.getValue()))
                ChunkData.removeChunk(level.dimension()
                    .location(), entry.getKey());
        }
    }

    private static boolean handleChunk(Level level, List<BlockTracker> blockTrackerList) {
        boolean flag = true;
        Iterator<BlockTracker> iterator = blockTrackerList.iterator();

        while (iterator.hasNext()) {
            BlockTracker blockTracker = iterator.next();
            BlockPos blockPos = blockTracker.getBlockPos();

            switch (blockTracker.getTicksLeft()) {
                case 0:
                    healBlockTracker(level, blockTracker);
                    blockTracker.setTicksLeft(blockTracker.getTicksLeft() - 1);
                    break;
                case -1:
                    iterator.remove();
                    break;
                default:
                    flag = false;
                    FluidState fluidState = level.getFluidState(blockPos);
                    if (filterBlocks(level.getBlockState(blockPos)) && (fluidState.isEmpty() || fluidState.isSource())) {
                        popBlockTracker(level, blockTracker);
                        blockTracker.setTicksLeft(-1);
                    } else blockTracker.setTicksLeft(blockTracker.getTicksLeft() - 1);
            }
        }

        return flag;
    }

    private static void healBlockTracker(Level level, BlockTracker blockTracker) {
        FluidState fluidState = level.getFluidState(blockTracker.getBlockPos());
        if (filterBlocks(level.getBlockState(blockTracker.getBlockPos())) && (fluidState.isEmpty() || fluidState.isSource()))
            return;

        if (blockTracker.getBlockState()
            .getBlock()
            .equals(Blocks.NETHER_PORTAL))
            level.setBlock(blockTracker.getBlockPos(), Blocks.FIRE.defaultBlockState(), 3);
        else level.setBlock(blockTracker.getBlockPos(), blockTracker.getBlockState(), 3);

        if (blockTracker.getCompoundTag() != null && blockTracker.getCompoundTag()
            .getAllKeys()
            .size() != 0)
            level.setBlockEntity(BlockEntity.loadStatic(blockTracker.getBlockPos(), blockTracker.getBlockState(), blockTracker.getCompoundTag()));
        level.playSound(null, blockTracker.getBlockPos()
            .getX(), blockTracker.getBlockPos()
            .getY(), blockTracker.getBlockPos()
            .getZ(), SoundEvents.BASALT_PLACE, SoundSource.BLOCKS, 0.8F, 0.9F);
    }

    private static void popBlockTracker(Level level, BlockTracker blockTracker) {
        BlockPos blockPos = blockTracker.getBlockPos();
        level.addFreshEntity(new ItemEntity(level, blockPos.getX(), blockPos.getY(), blockPos.getZ(), new ItemStack(blockTracker.getBlockState()
            .getBlock()
            .asItem())));

        if (blockTracker.getCompoundTag() != null && blockTracker.getCompoundTag()
            .contains("Items")) {
            ListTag items = blockTracker.getCompoundTag()
                .getList("Items", Tag.TAG_COMPOUND);
            if (items != null) {
                for (int i = 0; i < items.size(); i++) {
                    level.addFreshEntity(new ItemEntity(level, blockPos.getX(), blockPos.getY(), blockPos.getZ(), ItemStack.of(items.getCompound(i))));
                }
            }
        }
    }

    public static void healChunks(Set<ChunkPos> chunkPosSet) {
        Map<ResourceLocation, Map<ChunkPos, List<BlockTracker>>> damagedBlocks = ChunkData.getDamagedBlocks();
        AtomicInteger i = new AtomicInteger(1);
        for (Map.Entry<ResourceLocation, Map<ChunkPos, List<BlockTracker>>> locationMapEntry : damagedBlocks.entrySet()) {
            for (Map.Entry<ChunkPos, List<BlockTracker>> chunkPosListEntry : locationMapEntry.getValue()
                .entrySet()) {
                if (chunkPosSet.contains(chunkPosListEntry.getKey())) {
                    chunkPosListEntry.getValue()
                        .sort(new BlockTracker.BlockTrackerComparator());

                    chunkPosListEntry.getValue()
                        .forEach(blockTracker -> blockTracker.setTicksLeft(i.getAndIncrement()));
                }
            }
        }
    }

    public static void healChunks() {
        Map<ResourceLocation, Map<ChunkPos, List<BlockTracker>>> damagedBlocks = ChunkData.getDamagedBlocks();
        AtomicInteger i = new AtomicInteger(1);
        for (Map.Entry<ResourceLocation, Map<ChunkPos, List<BlockTracker>>> locationMapEntry : damagedBlocks.entrySet()) {
            for (Map.Entry<ChunkPos, List<BlockTracker>> chunkPosListEntry : locationMapEntry.getValue()
                .entrySet()) {
                if (ActiveChunks.containsChunk(locationMapEntry.getKey(), chunkPosListEntry.getKey())) {
                    chunkPosListEntry.getValue()
                        .sort(new BlockTracker.BlockTrackerComparator());

                    chunkPosListEntry.getValue()
                        .forEach(blockTracker -> blockTracker.setTicksLeft(i.getAndIncrement()));
                }
            }
        }
    }
}
