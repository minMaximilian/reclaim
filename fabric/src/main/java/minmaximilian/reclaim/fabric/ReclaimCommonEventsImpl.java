package minmaximilian.reclaim.fabric;

import java.util.List;

import com.mojang.brigadier.CommandDispatcher;

import minmaximilian.reclaim.ReclaimCommonEvents;
import minmaximilian.reclaim.event.BlockPlaceEvents;
import minmaximilian.reclaim.event.EntityEvents;
import minmaximilian.reclaim.event.ExplosionEvents;
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
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.chunk.LevelChunk;

public class ReclaimCommonEventsImpl {

    public static void register() {
        ServerWorldEvents.LOAD.register(ReclaimCommonEventsImpl::onServerStarting);
        ExplosionEvents.DETONATE.register(ReclaimCommonEventsImpl::onExplosion);
        ServerTickEvents.START_WORLD_TICK.register(ReclaimCommonEventsImpl::onWorldTick);
        ServerChunkEvents.CHUNK_LOAD.register(ReclaimCommonEventsImpl::onChunkLoad);
        ServerChunkEvents.CHUNK_UNLOAD.register(ReclaimCommonEventsImpl::onChunkUnload);
        CommandRegistrationCallback.EVENT.register(ReclaimCommonEventsImpl::onRegisterCommands);
        EntityEvents.STRUCK_BY_LIGHTNING.register(ReclaimCommonEventsImpl::onLightningStrike);
        BlockPlaceEvents.PLACE.register(ReclaimCommonEventsImpl::onBlockPlace);
    }

    public static void onBlockPlace(BlockPlaceContext blockPlaceContext) {
        ReclaimCommonEvents.onBlockPlace(blockPlaceContext.getLevel(), blockPlaceContext.getClickedPos());
    }

    public static void onServerStarting(MinecraftServer server, ServerLevel level) {
        ReclaimCommonEvents.onServerStarting(level);
    }

    public static void onExplosion(Level level, Explosion explosion, List<Entity> entities, double v) {
        ReclaimCommonEvents.onExplosion(level, explosion.getToBlow(), explosion);
    }

    public static void onWorldTick(ServerLevel level) {
        ReclaimCommonEvents.onLevelTick(level);
    }

    public static void onChunkLoad(ServerLevel serverLevel, LevelChunk levelChunk) {
        ReclaimCommonEvents.onChunkLoad(serverLevel, levelChunk);
    }

    public static void onChunkUnload(ServerLevel serverLevel, LevelChunk levelChunk) {
        ReclaimCommonEvents.onChunkUnload(serverLevel, levelChunk);
    }

    public static void onRegisterCommands(CommandDispatcher<CommandSourceStack> commandSourceStackCommandDispatcher,
        CommandBuildContext commandBuildContext, Commands.CommandSelection commandSelection) {
        ReclaimCommonEvents.onLoadCommands(commandSourceStackCommandDispatcher);
    }

    public static boolean onLightningStrike(Entity entity, LightningBolt lightningBolt) {
        return ReclaimCommonEvents.onLightningStrike(entity, lightningBolt);
    }
}
