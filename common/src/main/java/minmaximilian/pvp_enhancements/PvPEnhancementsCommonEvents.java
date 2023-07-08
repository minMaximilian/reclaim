package minmaximilian.pvp_enhancements;

import java.util.List;

import org.jetbrains.annotations.Nullable;

import com.mojang.brigadier.CommandDispatcher;

import minmaximilian.pvp_enhancements.regen.handlers.HandleBlockPlacement;
import minmaximilian.pvp_enhancements.regen.handlers.HandleChunkLoading;
import minmaximilian.pvp_enhancements.regen.handlers.HandleCommandRegistration;
import minmaximilian.pvp_enhancements.regen.handlers.HandleExplosion;
import minmaximilian.pvp_enhancements.regen.handlers.HandleLevelTick;
import minmaximilian.pvp_enhancements.regen.handlers.HandleLightningStrike;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LightningBolt;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.chunk.ChunkAccess;

public class PvPEnhancementsCommonEvents {
    public static void onExplosion(Level level, List<BlockPos> blockPosList, Explosion explosion) {
        HandleExplosion.handleExplosion(level, blockPosList, explosion);
    }

    public static void onChunkLoad(LevelAccessor level, ChunkAccess chunk) {
        if (level.isClientSide()) return;
        HandleChunkLoading.handleChunkLoading(level, chunk);
    }

    public static void onChunkUnload(LevelAccessor level, ChunkAccess chunk) {
        if (level.isClientSide()) return;
        HandleChunkLoading.handleChunkUnloading(level, chunk);
    }

    public static void onLevelTick(Level level) {
        if (level.isClientSide()) return;
        HandleLevelTick.handleLevelTick(level);
    }

    public static void onServerStarting(LevelAccessor level) {
        if (level.isClientSide()) return;
        PvPEnhancements.SAVED_CHUNKS.levelLoaded(level);
    }

    public static void onLoadCommands(CommandDispatcher<CommandSourceStack> dispatcher) {
        HandleCommandRegistration.registerCommands(dispatcher);
    }

    public static boolean onLightningStrike(Entity entity, LightningBolt lightning) {
        return HandleLightningStrike.handleLightningStrike(entity, lightning);
    }

    public static void onBlockPlace(LevelAccessor level, @Nullable Entity entity, BlockState placedBlock, BlockPos pos) {
        HandleBlockPlacement.handleBlockPlacement(level, entity, placedBlock, pos);
    }

    public static void onBlockPlace(Level level, BlockPos blockPos) {
        HandleBlockPlacement.handleBlockPlacement(level, blockPos);
    }
}
