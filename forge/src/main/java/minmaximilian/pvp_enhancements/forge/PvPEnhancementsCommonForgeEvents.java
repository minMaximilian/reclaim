package minmaximilian.pvp_enhancements.forge;

import minmaximilian.pvp_enhancements.PvPEnhancementsCommonEvents;
import minmaximilian.pvp_enhancements.item.Items;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.EntityStruckByLightningEvent;
import net.minecraftforge.event.entity.item.ItemExpireEvent;
import net.minecraftforge.event.level.ChunkEvent;
import net.minecraftforge.event.level.ExplosionEvent;
import net.minecraftforge.event.level.LevelEvent;
import net.minecraftforge.eventbus.api.IEventBus;

public class PvPEnhancementsCommonForgeEvents {
    public static void register(IEventBus forgeEventBus) {
        forgeEventBus.addListener(PvPEnhancementsCommonForgeEvents::onServerStarting);
        forgeEventBus.addListener(PvPEnhancementsCommonForgeEvents::onExplosion);
        forgeEventBus.addListener(PvPEnhancementsCommonForgeEvents::onWorldTick);
        forgeEventBus.addListener(PvPEnhancementsCommonForgeEvents::onChunkLoad);
        forgeEventBus.addListener(PvPEnhancementsCommonForgeEvents::onChunkUnload);
        forgeEventBus.addListener(PvPEnhancementsCommonForgeEvents::onRegisterCommands);
        forgeEventBus.addListener(PvPEnhancementsCommonForgeEvents::onLightningStrike);
        forgeEventBus.addListener(PvPEnhancementsCommonForgeEvents::onItemExpiry);
    }

    public static void onItemExpiry(ItemExpireEvent event) {
        if (event.getEntity().getItem().getItem() == Items.HEPHAESTUS_BAG.get())
            event.setCanceled(true);
    }

    public static void onServerStarting(LevelEvent.Load event) {
        PvPEnhancementsCommonEvents.onServerStarting(event.getLevel());
    }

    public static void onExplosion(ExplosionEvent.Detonate explosionEvent) {
        PvPEnhancementsCommonEvents.onExplosion(explosionEvent.getLevel(), explosionEvent.getAffectedBlocks(), explosionEvent.getExplosion());
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

    public static void onPenetration() {
        PvPEnhancementsCommonEvents.handlePenetration(new BlockPos(0, 0, 0), new BlockState(null, null, null));
    }
}
