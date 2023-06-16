package minmaximilian.pvp_enhancements.regen.handlers;

import org.jetbrains.annotations.Nullable;

import minmaximilian.pvp_enhancements.block.WallPlaster;
import minmaximilian.pvp_enhancements.regen.ChunkData;
import minmaximilian.pvp_enhancements.regen.ChunkTracker;
import minmaximilian.pvp_enhancements.regen.HealChunk;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

public class HandleBlockPlacement {
    public static void handleBlockPlacement(LevelAccessor level, @Nullable Entity entity, BlockState placedBlock, BlockPos blockPos) {
        if (entity instanceof Player) {
            Block block = placedBlock.getBlock();
            ChunkTracker chunkTracker = ChunkData.getChunkTracker(level.dimensionType().effectsLocation(), new ChunkPos((blockPos)));
            if (chunkTracker.get(blockPos) != null && block instanceof WallPlaster)
                HealChunk.healBlockTrackerWithoutPop(level.getServer().getLevel(ResourceKey.create(Registry.DIMENSION_REGISTRY, level.dimensionType().effectsLocation())), chunkTracker.remove(blockPos));
            else if (chunkTracker.get(blockPos) != null)
                HealChunk.popBlockTracker(level.getServer().getLevel(ResourceKey.create(Registry.DIMENSION_REGISTRY, level.dimensionType().effectsLocation())), chunkTracker.remove(blockPos));
        }
    }
}
