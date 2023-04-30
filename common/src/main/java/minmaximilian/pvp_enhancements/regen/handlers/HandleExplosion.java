package minmaximilian.pvp_enhancements.regen.handlers;

import java.util.List;
import java.util.stream.Collectors;

import minmaximilian.pvp_enhancements.config.PvPEnhancementsConfig;
import minmaximilian.pvp_enhancements.regen.util.BlockTracker;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Registry;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class HandleExplosion {
	private static final int delayInTicksBeforeHealingDamage = PvPEnhancementsConfig.COMMON.delayInTicksBeforeHealingDamage.get();
	private static final int ticksBetweenHeals = PvPEnhancementsConfig.COMMON.ticksBetweenHeals.get();
	private static final boolean healCreeperExplosions = PvPEnhancementsConfig.COMMON.healCreeperExplosions.get();
	public static void handleExplosion(Level level, List<BlockPos> blockPosList, Entity entity, LivingEntity mob) {
		if (!healCreeperExplosions && entity.getType() == EntityType.CREEPER) return;

		List<BlockTracker> blockTrackerList = blockPosList.stream()
			.filter(blockPos -> filterBlocks(level.getBlockState(blockPos)))
			.map(blockPos -> createBlockTrackers(level, blockPos))
			.sorted(new BlockTracker.BlockTrackerComparator())
			.collect(Collectors.toList());

		int i = 0;
		for (BlockTracker blockTracker : blockTrackerList) {
			blockTracker.setTicksLeft(delayInTicksBeforeHealingDamage + ticksBetweenHeals * i++);
		}

		blockTrackerList.stream().map(blockTracker -> {return 1;}).collect(Collectors.toList());
	}

	private static boolean filterBlocks(BlockState blockState) {
		switch (Registry.BLOCK.getKey(blockState.getBlock()).toString()) {
			case "minecraft:air", "minecraft:void_air", "minecraft:cave_air", "minecraft:fire":
				return false;
		}
		return true;
	}

	private static BlockTracker createBlockTrackers(Level level, BlockPos blockPos) {
		ResourceLocation resourceLocation = level.dimension().location();
		BlockEntity blockEntity = level.getBlockEntity(blockPos);
		CompoundTag compoundTag = null;
		if (blockEntity != null) {
			compoundTag = blockEntity.saveWithFullMetadata();
		}
		return new BlockTracker(
			resourceLocation,
			level.getBlockState(blockPos),
			compoundTag,
			blockPos,
			delayInTicksBeforeHealingDamage);
	}
}
