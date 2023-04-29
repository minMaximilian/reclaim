package minmaximilian.pvp_enhancements;

import minmaximilian.pvp_enhancements.regen.handlers.HandleExplosion;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

import java.util.List;

public class PvPEnhancementsCommonEvents {
	public static void onExplosion(Level level, List<BlockPos> blockPosList, Entity entity, LivingEntity mob) {
		HandleExplosion.handleExplosion(level, blockPosList, entity, mob);
	}
	public static void onLevelTick(Level level) {

	}


	public static void handlePenetration(BlockPos blockPos, BlockState blockState) {

	}
}
