package minmaximilian.reclaim.regen.util;

import java.util.HashSet;
import java.util.Set;

import net.minecraft.world.level.ChunkPos;

public class ChunkPosUtils {

    public static Set<ChunkPos> getAdjacentChunkPositions(ChunkPos chunkPos) {
        int x = chunkPos.x;
        int z = chunkPos.z;
        Set<ChunkPos> adjacentChunkPositions = new HashSet<>();

        for (int xOffset = -1; xOffset <= 1; xOffset++) {
            for (int zOffset = -1; zOffset <= 1; zOffset++) {
                adjacentChunkPositions.add(new ChunkPos(x + xOffset, z + zOffset));
            }
        }

        return adjacentChunkPositions;
    }
}
