package minmaximilian.pvp_enhancements;

import minmaximilian.pvp_enhancements.regen.handlers.HandleChunkLoading;
import minmaximilian.pvp_enhancements.regen.handlers.HandleExplosion;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.chunk.ChunkAccess;

import java.util.List;

public class PvPEnhancementsCommonEvents {
	public static void onExplosion(Level level, List<BlockPos> blockPosList, Entity entity, LivingEntity mob) {
		HandleExplosion.handleExplosion(level, blockPosList, entity, mob);
	}

	public static void onChunkLoad(LevelAccessor level, ChunkAccess chunk, CompoundTag data) {
		HandleChunkLoading.handleChunkLoading(level, chunk, data);
	}

	public static void onChunkSave(LevelAccessor level, ChunkAccess chunk, CompoundTag data) {
		HandleChunkLoading.handleChunkSaving(level, chunk, data);
	}

	public static void onLevelTick(Level level) {

	}

	public static void handlePenetration(BlockPos blockPos, BlockState blockState) {

	}
}
