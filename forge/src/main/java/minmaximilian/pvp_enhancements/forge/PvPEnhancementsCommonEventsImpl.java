package minmaximilian.pvp_enhancements.forge;

import minmaximilian.pvp_enhancements.PvPEnhancementsCommonEvents;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.EntityStruckByLightningEvent;
import net.minecraftforge.event.level.BlockEvent;
import net.minecraftforge.event.level.ChunkEvent;
import net.minecraftforge.event.level.ExplosionEvent;
import net.minecraftforge.event.level.LevelEvent;
import net.minecraftforge.eventbus.api.IEventBus;

public class PvPEnhancementsCommonEventsImpl {

    public static void register(IEventBus forgeEventBus) {
        forgeEventBus.addListener(PvPEnhancementsCommonEventsImpl::onServerStarting);
        forgeEventBus.addListener(PvPEnhancementsCommonEventsImpl::onExplosion);
        forgeEventBus.addListener(PvPEnhancementsCommonEventsImpl::onWorldTick);
        forgeEventBus.addListener(PvPEnhancementsCommonEventsImpl::onChunkLoad);
        forgeEventBus.addListener(PvPEnhancementsCommonEventsImpl::onChunkUnload);
        forgeEventBus.addListener(PvPEnhancementsCommonEventsImpl::onRegisterCommands);
        forgeEventBus.addListener(PvPEnhancementsCommonEventsImpl::onLightningStrike);
        forgeEventBus.addListener(PvPEnhancementsCommonEventsImpl::onBlockPlace);
    }

    public static void onServerStarting(LevelEvent.Load event) {
        PvPEnhancementsCommonEvents.onServerStarting(event.getLevel());
    }

    public static void onExplosion(ExplosionEvent.Detonate explosionEvent) {
        PvPEnhancementsCommonEvents.onExplosion(explosionEvent.getLevel(), explosionEvent.getAffectedBlocks(),
            explosionEvent.getExplosion());
    }

    private static void onBlockPlace(BlockEvent.EntityPlaceEvent blockEvent) {
        PvPEnhancementsCommonEvents.onBlockPlace(blockEvent.getLevel(), blockEvent.getEntity(),
            blockEvent.getPlacedBlock(), blockEvent.getPos());
    }

    public static void onWorldTick(TickEvent.LevelTickEvent event) {
        PvPEnhancementsCommonEvents.onLevelTick(event.level);
    }

    public static void onChunkLoad(ChunkEvent.Load event) {
        PvPEnhancementsCommonEvents.onChunkLoad(event.getLevel(), event.getChunk());
    }

    public static void onChunkUnload(ChunkEvent.Unload event) {
        PvPEnhancementsCommonEvents.onChunkUnload(event.getLevel(), event.getChunk());
    }

    public static void onRegisterCommands(RegisterCommandsEvent event) {
        PvPEnhancementsCommonEvents.onLoadCommands(event.getDispatcher());
    }

    public static void onLightningStrike(EntityStruckByLightningEvent event) {
        PvPEnhancementsCommonEvents.onLightningStrike(event.getEntity(), event.getLightning());
    }
}
