package minmaximilian.pvp_enhancements.regen.util;

import javax.annotation.Nullable;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.state.BlockState;

public class BlockTracker {
	private ResourceLocation resourceLocation;
	private BlockState blockState;
	private CompoundTag compoundTag;
	private BlockPos blockPos;
	private int ticksLeft;
	public BlockTracker(ResourceLocation resourceLocation, BlockState blockState, @Nullable CompoundTag compoundTag, BlockPos blockPos, int ticksLeft) {

		this.resourceLocation = resourceLocation;
		this.blockState = blockState;
		this.blockPos = blockPos;
		this.ticksLeft = ticksLeft;
	}

	public ResourceLocation getResourceLocation() {
		return resourceLocation;
	}

	public BlockState getBlockState() {
		return blockState;
	}

	public BlockPos getBlockPos() {
		return blockPos;
	}

	public int getTicksLeft() {
		return ticksLeft;
	}

	public void setTicksLeft(int ticksLeft) {
		this.ticksLeft = ticksLeft;
	}

	public CompoundTag getCompoundTag() {
		return compoundTag;
	}
}
