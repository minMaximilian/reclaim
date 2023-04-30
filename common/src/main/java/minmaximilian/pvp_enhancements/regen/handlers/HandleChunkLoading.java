package minmaximilian.pvp_enhancements.regen.handlers;

import minmaximilian.pvp_enhancements.regen.ActiveChunkData;
import minmaximilian.pvp_enhancements.regen.util.BlockTracker;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.NbtUtils;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.chunk.ChunkAccess;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class HandleChunkLoading {
	public static void handleChunkLoading(LevelAccessor level, ChunkAccess chunk, CompoundTag data) {
		if (data == null || !data.contains("needsHealing") || level == null) return;

		ResourceLocation resourceLocation = level.dimensionType().effectsLocation();

		if (resourceLocation == null) return;

		CompoundTag needsHealing = data.getCompound("needsHealing");

		List<BlockTracker> blockTrackers = needsHealing
			.getAllKeys()
			.stream()
			.map(key -> nbtToBlockTracker(key, needsHealing, resourceLocation))
			.collect(Collectors.toList());

		ActiveChunkData.upsertChunk(resourceLocation, chunk.getPos(), blockTrackers);
	}

	public static void handleChunkSaving(LevelAccessor level, ChunkAccess chunk, CompoundTag data) {
		ResourceLocation resourceLocation = level.dimensionType().effectsLocation();

		if (!ActiveChunkData.containsChunk(resourceLocation, chunk.getPos())) return;

		List<BlockTracker> savedBlocks = ActiveChunkData.getChunk(resourceLocation, chunk.getPos());

		AtomicInteger i = new AtomicInteger();
		List<CompoundTag> compoundTags = savedBlocks
			.stream()
			.map(blockTracker -> blockTrackerToNbt(blockTracker))
			.collect(Collectors.toList());

		CompoundTag chunkCompoundTag = new CompoundTag();
		for (CompoundTag compoundTag : compoundTags) {
			chunkCompoundTag.put(String.valueOf(i.getAndIncrement()), compoundTag);
		}

		data.put("needsHealing", chunkCompoundTag);
	}

	private static CompoundTag blockTrackerToNbt(BlockTracker blockTracker) {
		CompoundTag compoundTag = new CompoundTag();
		compoundTag.put("blockState", NbtUtils.writeBlockState(blockTracker.getBlockState()));
		CompoundTag blockTrackerCompoundTag = blockTracker.getCompoundTag();
		if (blockTrackerCompoundTag != null) {
			compoundTag.put("blockNbt", blockTrackerCompoundTag);
		}
		compoundTag.put("blockPos", NbtUtils.writeBlockPos(blockTracker.getBlockPos()));
		compoundTag.putInt("ticksLeft", blockTracker.getTicksLeft());

		return compoundTag;
	}

	private static BlockTracker nbtToBlockTracker(String key, CompoundTag chunkData, ResourceLocation resourceLocation) {
		CompoundTag compoundTag = chunkData.getCompound(key);
		BlockState blockState = NbtUtils.readBlockState(compoundTag.getCompound("blockState"));
		CompoundTag blockNbt = compoundTag.getCompound("blockNbt");
		BlockPos blockPos = NbtUtils.readBlockPos(compoundTag.getCompound("blockPos"));
		int ticksLeft = compoundTag.getInt("ticksLeft");

		return new BlockTracker(resourceLocation, blockState, blockNbt, blockPos, ticksLeft);
	}
}
