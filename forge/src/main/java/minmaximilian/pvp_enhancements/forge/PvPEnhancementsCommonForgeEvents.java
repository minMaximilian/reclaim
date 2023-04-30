package minmaximilian.pvp_enhancements.forge;

import minmaximilian.pvp_enhancements.PvPEnhancementsCommonEvents;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.level.ChunkDataEvent;
import net.minecraftforge.event.level.ExplosionEvent;
import net.minecraftforge.eventbus.api.IEventBus;

public class PvPEnhancementsCommonForgeEvents {
	public static void register(IEventBus forgeEventBus) {
		forgeEventBus.addListener(PvPEnhancementsCommonForgeEvents::onExplosion);
		forgeEventBus.addListener(PvPEnhancementsCommonForgeEvents::onWorldTick);
		forgeEventBus.addListener(PvPEnhancementsCommonForgeEvents::onChunkLoad);
		forgeEventBus.addListener(PvPEnhancementsCommonForgeEvents::onChunkSave);
	}
	public static void onExplosion(ExplosionEvent.Detonate explosionEvent) {
		PvPEnhancementsCommonEvents.onExplosion(explosionEvent.getLevel(), explosionEvent.getAffectedBlocks(), explosionEvent.getExplosion().getExploder(), explosionEvent.getExplosion().getSourceMob());
	}
	public static void onChunkLoad(ChunkDataEvent.Load event) {
		PvPEnhancementsCommonEvents.onChunkLoad(event.getLevel(), event.getChunk(), event.getData());
	}
	public static void onChunkSave(ChunkDataEvent.Save event) {
		PvPEnhancementsCommonEvents.onChunkSave(event.getLevel(), event.getChunk(), event.getData());
	}
	public static void onWorldTick(TickEvent.LevelTickEvent event) {
		PvPEnhancementsCommonEvents.onLevelTick(event.level);
	}
	public static void onPenetration() {
		PvPEnhancementsCommonEvents.handlePenetration(new BlockPos(0,0,0), new BlockState(null, null, null));
	}
}
