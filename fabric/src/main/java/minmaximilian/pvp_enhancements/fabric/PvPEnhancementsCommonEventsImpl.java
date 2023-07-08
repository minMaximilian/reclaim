package minmaximilian.pvp_enhancements.fabric;

import java.util.List;

import com.mojang.brigadier.CommandDispatcher;

import minmaximilian.pvp_enhancements.PvPEnhancementsCommonEvents;
import minmaximilian.pvp_enhancements.event.BlockPlaceEvents;
import minmaximilian.pvp_enhancements.event.EntityEvents;
import minmaximilian.pvp_enhancements.event.ExplosionEvents;
import minmaximilian.pvp_enhancements.item.PvPEnhancementsItems;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerChunkEvents;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerWorldEvents;
import net.minecraft.commands.CommandBuildContext;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LightningBolt;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.chunk.LevelChunk;

public class PvPEnhancementsCommonEventsImpl {

    public static void register() {
        ServerWorldEvents.LOAD.register(PvPEnhancementsCommonEventsImpl::onServerStarting);
        ExplosionEvents.DETONATE.register(PvPEnhancementsCommonEventsImpl::onExplosion);
        ServerTickEvents.START_WORLD_TICK.register(PvPEnhancementsCommonEventsImpl::onWorldTick);
        ServerChunkEvents.CHUNK_LOAD.register(PvPEnhancementsCommonEventsImpl::onChunkLoad);
        ServerChunkEvents.CHUNK_UNLOAD.register(PvPEnhancementsCommonEventsImpl::onChunkUnload);
        CommandRegistrationCallback.EVENT.register(PvPEnhancementsCommonEventsImpl::onRegisterCommands);
        EntityEvents.STRUCK_BY_LIGHTNING.register(PvPEnhancementsCommonEventsImpl::onLightningStrike);
        BlockPlaceEvents.PLACE.register(PvPEnhancementsCommonEventsImpl::onBlockPlace);
        EntityEvents.ITEM_DESPAWN_EVENT.register(PvPEnhancementsCommonEventsImpl::onItemDespawn);
    }

    public static boolean onItemDespawn(ItemEntity itemEntity) {
        if (itemEntity.getItem().getItem() == PvPEnhancementsItems.HEPHAESTUS_BAG.get()) {
            itemEntity.setUnlimitedLifetime();
            return true;
        }
        return false;
    }

    public static void onBlockPlace(BlockPlaceContext blockPlaceContext) {
        PvPEnhancementsCommonEvents.onBlockPlace(blockPlaceContext.getLevel(), blockPlaceContext.getClickedPos());
    }

    public static void onServerStarting(MinecraftServer server, ServerLevel level) {
        PvPEnhancementsCommonEvents.onServerStarting(level);
    }

    public static void onExplosion(Level level, Explosion explosion, List<Entity> entities, double v) {
        PvPEnhancementsCommonEvents.onExplosion(level, explosion.getToBlow(), explosion);
    }

    public static void onWorldTick(ServerLevel level) {
        PvPEnhancementsCommonEvents.onLevelTick(level);
    }

    public static void onChunkLoad(ServerLevel serverLevel, LevelChunk levelChunk) {
        PvPEnhancementsCommonEvents.onChunkLoad(serverLevel, levelChunk);
    }

    public static void onChunkUnload(ServerLevel serverLevel, LevelChunk levelChunk) {
        PvPEnhancementsCommonEvents.onChunkUnload(serverLevel, levelChunk);
    }

    public static void onRegisterCommands(CommandDispatcher<CommandSourceStack> commandSourceStackCommandDispatcher,
        CommandBuildContext commandBuildContext, Commands.CommandSelection commandSelection) {
        PvPEnhancementsCommonEvents.onLoadCommands(commandSourceStackCommandDispatcher);
    }

    public static boolean onLightningStrike(Entity entity, LightningBolt lightningBolt) {
        return PvPEnhancementsCommonEvents.onLightningStrike(entity, lightningBolt);
    }
}
