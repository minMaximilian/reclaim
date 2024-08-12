package minmaximilian.reclaim;

import java.util.List;

import org.jetbrains.annotations.Nullable;

import com.mojang.brigadier.CommandDispatcher;

import minmaximilian.reclaim.item.ReclaimItems;
import minmaximilian.reclaim.regen.handlers.HandleBlockPlacement;
import minmaximilian.reclaim.regen.handlers.HandleChunkLoading;
import minmaximilian.reclaim.regen.handlers.HandleCommandRegistration;
import minmaximilian.reclaim.regen.handlers.HandleExplosion;
import minmaximilian.reclaim.regen.handlers.HandleLevelTick;
import minmaximilian.reclaim.regen.handlers.HandleLightningStrike;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LightningBolt;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.chunk.ChunkAccess;

public class ReclaimCommonEvents {

    public static boolean onItemDespawn(ItemEntity itemEntity, int pickupDelay) {
        if (itemEntity.getItem().getItem() == ReclaimItems.HEPHAESTUS_BAG.get() && pickupDelay != 32767) {
            itemEntity.setUnlimitedLifetime();
            return false;
        }
        return true;
    }

    public static void onExplosion(Level level, List<BlockPos> blockPosList, Explosion explosion) {
        HandleExplosion.handleExplosion(level, blockPosList, explosion);
    }

    public static void onChunkLoad(LevelAccessor level, ChunkAccess chunk) {
        if (level.isClientSide()) {
            return;
        }
        HandleChunkLoading.handleChunkLoading(level, chunk);
    }

    public static void onChunkUnload(LevelAccessor level, ChunkAccess chunk) {
        if (level.isClientSide()) {
            return;
        }
        HandleChunkLoading.handleChunkUnloading(level, chunk);
    }

    public static void onLevelTick(Level level) {
        if (level.isClientSide()) {
            return;
        }
        HandleLevelTick.handleLevelTick(level);
    }

    public static void onServerStarting(LevelAccessor level) {
        if (level.isClientSide()) {
            return;
        }
        Reclaim.SAVED_CHUNKS.levelLoaded(level);
    }

    public static void onLoadCommands(CommandDispatcher<CommandSourceStack> dispatcher) {
        HandleCommandRegistration.registerCommands(dispatcher);
    }

    public static boolean onLightningStrike(Entity entity, LightningBolt lightning) {
        return HandleLightningStrike.handleLightningStrike(entity, lightning);
    }

    public static void onBlockPlace(LevelAccessor level, @Nullable Entity entity, BlockState placedBlock,
        BlockPos pos) {
        HandleBlockPlacement.handleBlockPlacement(level, entity, placedBlock, pos);
    }

    public static void onBlockPlace(Level level, BlockPos blockPos) {
        HandleBlockPlacement.handleBlockPlacement(level, blockPos);
    }
}
