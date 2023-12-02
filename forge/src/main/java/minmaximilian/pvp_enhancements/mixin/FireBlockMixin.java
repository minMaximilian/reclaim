package minmaximilian.pvp_enhancements.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import minmaximilian.pvp_enhancements.compat.handlers.FireHandler;
import minmaximilian.pvp_enhancements.config.PvPEnhancementsConfig;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.FireBlock;

@Mixin(FireBlock.class)
public class FireBlockMixin {

    @Inject(method = "tryCatchFire", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/block/FireBlock;getStateWithAge(Lnet/minecraft/world/level/LevelAccessor;Lnet/minecraft/core/BlockPos;I)Lnet/minecraft/world/level/block/state/BlockState;"))
    public void maxs_pvp_enhancements$setBlockCheckBurnOut(Level level, BlockPos blockPos, int chance,
        RandomSource random,
        int age, Direction face, CallbackInfo info) {
        if (PvPEnhancementsConfig.COMMON.healFires.get() && !level.isClientSide()) {
            FireHandler.handleFire(level, blockPos);
        }
    }

    @Inject(method = "tryCatchFire", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/Level;removeBlock(Lnet/minecraft/core/BlockPos;Z)Z"))
    public void maxs_pvp_enhancements$removeBlockCheckBurnOut(Level level, BlockPos blockPos, int chance,
        RandomSource random,
        int age, Direction face, CallbackInfo info) {
        if (PvPEnhancementsConfig.COMMON.healFires.get() && !level.isClientSide()) {
            FireHandler.handleFire(level, blockPos);
        }
    }
}
