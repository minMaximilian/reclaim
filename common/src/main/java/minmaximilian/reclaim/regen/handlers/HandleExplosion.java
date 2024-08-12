package minmaximilian.reclaim.regen.handlers;

import static minmaximilian.reclaim.regen.util.LegalPlacements.filterBlock;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

import minmaximilian.reclaim.config.ReclaimConfig;
import minmaximilian.reclaim.regen.ChunkData;
import minmaximilian.reclaim.regen.util.BlockTracker;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class HandleExplosion {

    private static final boolean healCreeperExplosions = ReclaimConfig.COMMON.healCreeperExplosions.get();

    public static void handleExplosion(Level level, List<BlockPos> blockPosList, Explosion explosion) {
        if (!healCreeperExplosions && explosion.getDirectSourceEntity() != null && explosion.getDirectSourceEntity()
            .getType() == EntityType.CREEPER) {
            return;
        }

        List<BlockTracker> blockTrackerList = blockPosList.stream()
            .filter(blockPos -> filterBlock(level.getBlockState(blockPos), explosion))
            .map(blockPos -> createBlockTrackers(level, blockPos))
            .collect(Collectors.toList());

        blockTrackerList
            .forEach(blockTracker -> {
                level.removeBlockEntity(blockTracker.getBlockPos());
                level.setBlock(blockTracker.getBlockPos(), Blocks.AIR.defaultBlockState(), 3);
            });

        Map<ChunkPos, List<BlockTracker>> needHealing = new ConcurrentHashMap<>();
        for (BlockTracker blockTracker : blockTrackerList) {
            if (!needHealing.containsKey(new ChunkPos(blockTracker.getBlockPos()))) {
                needHealing.put(new ChunkPos(blockTracker.getBlockPos()), new ArrayList<>());
            }
            needHealing.get(new ChunkPos(blockTracker.getBlockPos()))
                .add(blockTracker);
        }

        ChunkData.upsertExplosion(level.dimension()
            .location(), needHealing);
    }

    private static BlockTracker createBlockTrackers(Level level, BlockPos blockPos) {
        BlockEntity blockEntity = level.getBlockEntity(blockPos);
        BlockState blockState = level.getBlockState(blockPos);
        CompoundTag compoundTag = null;
        if (blockEntity != null) {
            compoundTag = blockEntity.saveWithFullMetadata();
        }
        return new BlockTracker(blockState, compoundTag, blockPos);
    }
}
