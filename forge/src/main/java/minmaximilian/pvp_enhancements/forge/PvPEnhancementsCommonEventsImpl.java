package minmaximilian.pvp_enhancements.forge;

import minmaximilian.pvp_enhancements.PvPEnhancementsCommonEvents;
import minmaximilian.pvp_enhancements.item.PvPEnhancementsItems;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.EntityStruckByLightningEvent;
import net.minecraftforge.event.entity.item.ItemExpireEvent;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.event.world.ChunkEvent;
import net.minecraftforge.event.world.ExplosionEvent;
import net.minecraftforge.event.world.WorldEvent;
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
        forgeEventBus.addListener(PvPEnhancementsCommonEventsImpl::onItemExpiry);
        forgeEventBus.addListener(PvPEnhancementsCommonEventsImpl::onBlockPlace);
    }

    public static void onItemExpiry(ItemExpireEvent event) {
        if (event.getEntityItem().getItem().getItem() == PvPEnhancementsItems.HEPHAESTUS_BAG.get()) {
            event.setCanceled(true);
            event.getEntityItem().setUnlimitedLifetime();
        }
    }

    public static void onServerStarting(WorldEvent.Load event) {
        PvPEnhancementsCommonEvents.onServerStarting(event.getWorld());
    }

    public static void onExplosion(ExplosionEvent.Detonate explosionEvent) {
        PvPEnhancementsCommonEvents.onExplosion(explosionEvent.getWorld(), explosionEvent.getAffectedBlocks(),
            explosionEvent.getExplosion());
    }

    private static void onBlockPlace(BlockEvent.EntityPlaceEvent blockEvent) {
        PvPEnhancementsCommonEvents.onBlockPlace(blockEvent.getWorld(), blockEvent.getEntity(),
            blockEvent.getPlacedBlock(), blockEvent.getPos());
    }

    public static void onWorldTick(TickEvent.WorldTickEvent event) {
        PvPEnhancementsCommonEvents.onLevelTick(event.world);
    }

    public static void onChunkLoad(ChunkEvent.Load event) {
        PvPEnhancementsCommonEvents.onChunkLoad(event.getWorld(), event.getChunk());
    }

    public static void onChunkUnload(ChunkEvent.Unload event) {
        PvPEnhancementsCommonEvents.onChunkUnload(event.getWorld(), event.getChunk());
    }

    public static void onRegisterCommands(RegisterCommandsEvent event) {
        PvPEnhancementsCommonEvents.onLoadCommands(event.getDispatcher());
    }

    public static void onLightningStrike(EntityStruckByLightningEvent event) {
        PvPEnhancementsCommonEvents.onLightningStrike(event.getEntity(), event.getLightning());
    }
}
