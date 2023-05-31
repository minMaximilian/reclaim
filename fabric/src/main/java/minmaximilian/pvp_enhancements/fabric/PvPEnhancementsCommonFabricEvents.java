package minmaximilian.pvp_enhancements.fabric;

import java.util.List;

import com.mojang.brigadier.CommandDispatcher;

import minmaximilian.pvp_enhancements.PvPEnhancementsCommonEvents;
import minmaximilian.pvp_enhancements.compat.Mods;
import minmaximilian.pvp_enhancements.fabric.event.EntityEvents;
import minmaximilian.pvp_enhancements.fabric.event.ExplosionEvents;
import minmaximilian.pvp_enhancements.item.Items;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerChunkEvents;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerEntityEvents;
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
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.chunk.LevelChunk;
import rbasamoyai.createbigcannons.fabric.events.OnCannonBreakBlockEvent;
import rbasamoyai.createbigcannons.multiloader.event_classes.OnCannonBreakBlock;

public class PvPEnhancementsCommonFabricEvents {

    public static void register() {
        ServerWorldEvents.LOAD.register(PvPEnhancementsCommonFabricEvents::onServerStarting);
        ExplosionEvents.DETONATE.register(PvPEnhancementsCommonFabricEvents::onExplosion);
        ServerTickEvents.START_WORLD_TICK.register(PvPEnhancementsCommonFabricEvents::onWorldTick);
        ServerChunkEvents.CHUNK_LOAD.register(PvPEnhancementsCommonFabricEvents::onChunkLoad);
        ServerChunkEvents.CHUNK_UNLOAD.register(PvPEnhancementsCommonFabricEvents::onChunkUnload);
        CommandRegistrationCallback.EVENT.register(PvPEnhancementsCommonFabricEvents::onRegisterCommands);
        EntityEvents.STRUCK_BY_LIGHTNING.register(PvPEnhancementsCommonFabricEvents::onLightningStrike);
        ServerEntityEvents.ENTITY_UNLOAD.register(PvPEnhancementsCommonFabricEvents::onItemExpiry);
        Mods.CREATEBIGCANNONS.executeIfInstalled(() -> {
            OnCannonBreakBlockEvent.EVENT.register(PvPEnhancementsCommonFabricEvents::onPenetration);
            return () -> {
            };
        });
    }

    public static void onItemExpiry(Entity entity, ServerLevel serverLevel) {
        if (entity instanceof ItemEntity item)
            if (item.getItem().getItem() == Items.HEPHAESTUS_BAG.get())
                item.setUnlimitedLifetime();
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

    public static void onRegisterCommands(CommandDispatcher<CommandSourceStack> commandSourceStackCommandDispatcher, CommandBuildContext commandBuildContext, Commands.CommandSelection commandSelection) {
        PvPEnhancementsCommonEvents.onLoadCommands(commandSourceStackCommandDispatcher);
    }

    public static boolean onLightningStrike(Entity entity, LightningBolt lightningBolt) {
        PvPEnhancementsCommonEvents.onLightningStrike(entity, lightningBolt);
        return true;
    }

    public static void onPenetration(OnCannonBreakBlock onCannonBreakBlock) {
        PvPEnhancementsCommonEvents.handlePenetration(onCannonBreakBlock);
    }
}
