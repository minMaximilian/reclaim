package minmaximilian.pvp_enhancements.regen;

import static minmaximilian.pvp_enhancements.PvPEnhancements.MOD_ID;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import minmaximilian.pvp_enhancements.regen.util.BlockTracker;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.NbtUtils;
import net.minecraft.nbt.Tag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.saveddata.SavedData;

public class SavedChunkData extends SavedData {
    public static SavedChunkData load(MinecraftServer server) {
        return server.overworld()
            .getDataStorage()
            .computeIfAbsent(
                SavedChunkData::load,
                SavedChunkData::new,
                MOD_ID
            );
    }

    private static SavedChunkData load(CompoundTag compoundTag) {
        ListTag resourceLocations = compoundTag.getList("resourceLocations", Tag.TAG_COMPOUND);
        for (int i = 0; i < resourceLocations.size(); i++) {
            CompoundTag resourceLocation = resourceLocations.getCompound(i);
            ResourceLocation resourceName = new ResourceLocation(resourceLocation.get("resourceName")
                .getAsString());
            ListTag chunks = resourceLocation.getList("chunks", Tag.TAG_COMPOUND);
            for (int j = 0; j < chunks.size(); j++) {
                CompoundTag chunk = chunks.getCompound(j);
                CompoundTag chunkTrackerTag = chunk.getCompound("chunkData");
                ChunkData.upsertChunk(resourceName, new ChunkPos(chunk.getLong("chunkPos")), nbtToBlockTrackerList(chunkTrackerTag.getList("blockTrackers", Tag.TAG_COMPOUND)));
                ChunkData.getChunkTrackers().get(resourceName).get(new ChunkPos(chunk.getLong("chunkPos"))).setTicksLeft(chunkTrackerTag.getInt("time"));
            }
        }

        return new SavedChunkData();
    }

    private static List<BlockTracker> nbtToBlockTrackerList(ListTag listTag) {
        List<BlockTracker> blockTrackerList = new ArrayList<>();
        for (int i = 0; i < listTag.size(); i++) {
            blockTrackerList.add(nbtToBlockTracker(listTag.getCompound(i)));
        }
        return blockTrackerList;
    }

    private static BlockTracker nbtToBlockTracker(CompoundTag blockTrackerTag) {
        BlockState blockState = NbtUtils.readBlockState(blockTrackerTag.getCompound("blockState"));
        CompoundTag blockNbt = blockTrackerTag.getCompound("blockNbt");
        BlockPos blockPos = NbtUtils.readBlockPos(blockTrackerTag.getCompound("blockPos"));
        int ticksLeft = blockTrackerTag.getInt("ticksLeft");

        return new BlockTracker(blockState, blockNbt, blockPos, ticksLeft);
    }

    private static CompoundTag chunkTrackerToNbt(ChunkTracker chunkTracker) {
        CompoundTag compoundTag = new CompoundTag();
        compoundTag.putInt("time", chunkTracker.getTicksLeft());
        compoundTag.put("blockTrackers", blockTrackerToNbt(chunkTracker.getBlockTrackers()));
        return compoundTag;
    }

    private static ListTag blockTrackerToNbt(List<BlockTracker> blockTrackerList) {
        ListTag list = new ListTag();
        for (BlockTracker blockTracker : blockTrackerList) {
            CompoundTag compoundTag = new CompoundTag();
            compoundTag.put("blockState", NbtUtils.writeBlockState(blockTracker.getBlockState()));
            CompoundTag blockTrackerCompoundTag = blockTracker.getCompoundTag();
            if (blockTrackerCompoundTag != null) {
                compoundTag.put("blockNbt", blockTrackerCompoundTag);
            }
            compoundTag.put("blockPos", NbtUtils.writeBlockPos(blockTracker.getBlockPos()));
            compoundTag.putInt("ticksLeft", blockTracker.getTicksLeft());
            list.add(compoundTag);
        }
        return list;
    }

    @Override
    public CompoundTag save(CompoundTag compoundTag) {
        ListTag resourceLocations = new ListTag();
        for (Map.Entry<ResourceLocation, Map<ChunkPos, ChunkTracker>> resourceLocationMapEntry : ChunkData.getChunkTrackers()
            .entrySet()) {
            ListTag chunks = new ListTag();
            for (Map.Entry<ChunkPos, ChunkTracker> entry : resourceLocationMapEntry.getValue()
                .entrySet()) {
                CompoundTag entryTag = new CompoundTag();
                entryTag.putLong("chunkPos", entry.getKey()
                    .toLong());
                entryTag.put("chunkData", chunkTrackerToNbt(entry.getValue()));
                chunks.add(entryTag);
            }
            CompoundTag entryTag = new CompoundTag();
            entryTag.putString("resourceName", resourceLocationMapEntry.getKey()
                .toString());
            entryTag.put("chunks", chunks);
            resourceLocations.add(entryTag);
        }
        compoundTag.put("resourceLocations", resourceLocations);
        return compoundTag;
    }
}
