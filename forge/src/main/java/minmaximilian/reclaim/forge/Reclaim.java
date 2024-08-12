package minmaximilian.reclaim.forge;

import minmaximilian.reclaim.ReclaimCommonEvents;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.EntityStruckByLightningEvent;
import net.minecraftforge.event.level.BlockEvent;
import net.minecraftforge.event.level.ChunkEvent;
import net.minecraftforge.event.level.ExplosionEvent;
import net.minecraftforge.event.level.LevelEvent;
import net.minecraftforge.eventbus.api.IEventBus;

public class ReclaimCommonEventsImpl {

    public static void register(IEventBus forgeEventBus) {
        forgeEventBus.addListener(ReclaimCommonEventsImpl::onServerStarting);
        forgeEventBus.addListener(ReclaimCommonEventsImpl::onExplosion);
        forgeEventBus.addListener(ReclaimCommonEventsImpl::onWorldTick);
        forgeEventBus.addListener(ReclaimCommonEventsImpl::onChunkLoad);
        forgeEventBus.addListener(ReclaimCommonEventsImpl::onChunkUnload);
        forgeEventBus.addListener(ReclaimCommonEventsImpl::onRegisterCommands);
        forgeEventBus.addListener(ReclaimCommonEventsImpl::onLightningStrike);
        forgeEventBus.addListener(ReclaimCommonEventsImpl::onBlockPlace);
    }

    public static void onServerStarting(LevelEvent.Load event) {
        ReclaimCommonEvents.onServerStarting(event.getLevel());
    }

    public static void onExplosion(ExplosionEvent.Detonate explosionEvent) {
        ReclaimCommonEvents.onExplosion(explosionEvent.getLevel(), explosionEvent.getAffectedBlocks(),
            explosionEvent.getExplosion());
    }

    private static void onBlockPlace(BlockEvent.EntityPlaceEvent blockEvent) {
        ReclaimCommonEvents.onBlockPlace(blockEvent.getLevel(), blockEvent.getEntity(),
            blockEvent.getPlacedBlock(), blockEvent.getPos());
    }

    public static void onWorldTick(TickEvent.LevelTickEvent event) {
        ReclaimCommonEvents.onLevelTick(event.level);
    }

    public static void onChunkLoad(ChunkEvent.Load event) {
        ReclaimCommonEvents.onChunkLoad(event.getLevel(), event.getChunk());
    }

    public static void onChunkUnload(ChunkEvent.Unload event) {
        ReclaimCommonEvents.onChunkUnload(event.getLevel(), event.getChunk());
    }

    public static void onRegisterCommands(RegisterCommandsEvent event) {
        ReclaimCommonEvents.onLoadCommands(event.getDispatcher());
    }

    public static void onLightningStrike(EntityStruckByLightningEvent event) {
        ReclaimCommonEvents.onLightningStrike(event.getEntity(), event.getLightning());
    }
}
