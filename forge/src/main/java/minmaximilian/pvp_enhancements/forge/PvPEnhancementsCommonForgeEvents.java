package minmaximilian.pvp_enhancements.forge;

import minmaximilian.pvp_enhancements.PvPEnhancementsCommonEvents;
import net.minecraftforge.event.level.ExplosionEvent;
import net.minecraftforge.eventbus.api.IEventBus;

public class PvPEnhancementsCommonForgeEvents {
	public static void register(IEventBus forgeEventBus) {
		forgeEventBus.addListener(PvPEnhancementsCommonForgeEvents::onExplosion);
	}

	public static void onExplosion(ExplosionEvent.Detonate explosionEvent) {
		PvPEnhancementsCommonEvents.handleExplosion(explosionEvent.getLevel(), explosionEvent.getAffectedBlocks(), explosionEvent.getExplosion().getExploder(), explosionEvent.getExplosion().getSourceMob());
	}
}
