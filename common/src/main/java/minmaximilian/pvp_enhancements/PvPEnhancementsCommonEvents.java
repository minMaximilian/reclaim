package minmaximilian.pvp_enhancements;

import java.util.List;

import com.mojang.brigadier.CommandDispatcher;

import minmaximilian.pvp_enhancements.regen.handlers.HandleChunkLoading;
import minmaximilian.pvp_enhancements.regen.handlers.HandleCommandRegistration;
import minmaximilian.pvp_enhancements.regen.handlers.HandleExplosion;
import minmaximilian.pvp_enhancements.regen.handlers.HandleLevelTick;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.chunk.ChunkAccess;

public class PvPEnhancementsCommonEvents {
    public static void onExplosion(Level level, List<BlockPos> blockPosList, Entity entity) {
        HandleExplosion.handleExplosion(level, blockPosList, entity);
    }

    public static void onChunkLoad(LevelAccessor level, ChunkAccess chunk) {
        HandleChunkLoading.handleChunkLoading(level, chunk);
    }

    public static void onChunkUnload(LevelAccessor level, ChunkAccess chunk) {
        HandleChunkLoading.handleChunkUnloading(level, chunk);
    }

    public static void onLevelTick(Level level) {
        HandleLevelTick.handleLevelTick(level);
    }

    public static void handlePenetration(BlockPos blockPos, BlockState blockState) {

    }

    public static void onServerStarting(LevelAccessor levelAccessor) {
        PvPEnhancements.SAVED_CHUNKS.levelLoaded(levelAccessor);
    }

    public static void onLoadCommands(CommandDispatcher<CommandSourceStack> dispatcher) {
        HandleCommandRegistration.registerCommands(dispatcher);
    }
}
