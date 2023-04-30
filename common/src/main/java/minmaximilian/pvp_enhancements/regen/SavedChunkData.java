package minmaximilian.pvp_enhancements.regen;

import minmaximilian.pvp_enhancements.regen.util.BlockTracker;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.NbtUtils;
import net.minecraft.nbt.Tag;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.saveddata.SavedData;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

import static minmaximilian.pvp_enhancements.PvPEnhancements.MOD_ID;

public class SavedChunkData extends SavedData {
    private final Map<ChunkPos, List<BlockTracker>> chunkData;

    private SavedChunkData() {
        this(new HashMap<>());
    }

    private SavedChunkData(Map<ChunkPos, List<BlockTracker>> chunkData) {
        this.chunkData = chunkData;
    }

    public static SavedChunkData load(MinecraftServer server) {
        return server.overworld()
            .getDataStorage()
            .computeIfAbsent(
                SavedChunkData::load,
                SavedChunkData::new,
                MOD_ID
            );
    }

    @Override
    public CompoundTag save(CompoundTag compoundTag) {
        ListTag list = new ListTag();
        for (Map.Entry<ChunkPos, List<BlockTracker>> entry : chunkData.entrySet()) {
            CompoundTag entryTag = new CompoundTag();
            entryTag.putLong("chunkPos", entry.getKey().toLong());
            for (BlockTracker blockTracker : entry.getValue()) {
                entryTag.put("chunkData", blockTrackerToNbt(blockTracker));
            }
            list.add(entryTag);
        }
        compoundTag.put("chunkData", list);
        return compoundTag;
    }

    public Map<ChunkPos, List<BlockTracker>> getChunkData() {
        return chunkData;
    }

    private static SavedChunkData load(CompoundTag compoundTag) {
        Map<ChunkPos, List<BlockTracker>> chunkData = new ConcurrentHashMap<>();
        ListTag list = compoundTag.getList("chunkData", Tag.TAG_COMPOUND);
        for (int i = 0; i < list.size(); i++) {
            CompoundTag entry = list.getCompound(i);
            ChunkPos chunkPos = new ChunkPos(entry.getLong("chunkPos"));
            List<BlockTracker> blockTrackerList = entry
                .getList("chunkPos", compoundTag.TAG_COMPOUND)
                .stream()
                .map(e -> nbtToBlockTracker((CompoundTag) e))
                .collect(Collectors.toList());
            chunkData.put(chunkPos, blockTrackerList);
        }
        return new SavedChunkData(chunkData);
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

    private static BlockTracker nbtToBlockTracker(CompoundTag blockTrackerTag) {
        BlockState blockState = NbtUtils.readBlockState(blockTrackerTag.getCompound("blockState"));
        CompoundTag blockNbt = blockTrackerTag.getCompound("blockNbt");
        BlockPos blockPos = NbtUtils.readBlockPos(blockTrackerTag.getCompound("blockPos"));
        int ticksLeft = blockTrackerTag.getInt("ticksLeft");

        return new BlockTracker(blockState, blockNbt, blockPos, ticksLeft);
    }
}
