package minmaximilian.pvp_enhancements.regen;

import java.util.concurrent.ConcurrentHashMap;

public class ChunkData {
	private static ConcurrentHashMap<Integer, Integer> damagedBlocks;

	public static ConcurrentHashMap<Integer, Integer> getDamagedBlocks() {
		return damagedBlocks;
	}

	public static void setDamagedBlocks(ConcurrentHashMap<Integer, Integer> damagedBlocks) {
		ChunkData.damagedBlocks = damagedBlocks;
	}
}
