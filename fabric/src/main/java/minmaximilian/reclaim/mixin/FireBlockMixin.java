package minmaximilian.reclaim.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import minmaximilian.reclaim.compat.handlers.FireHandler;
import minmaximilian.reclaim.config.ReclaimConfig;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.FireBlock;

@Mixin(FireBlock.class)
public class FireBlockMixin {

    @Inject(method = "checkBurnOut", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/Level;setBlock(Lnet/minecraft/core/BlockPos;Lnet/minecraft/world/level/block/state/BlockState;I)Z"))
    public void reclaim$setBlockCheckBurnOut(Level level, BlockPos blockPos, int chance,
        RandomSource random,
        int age, CallbackInfo ci) {
        if (ReclaimConfig.COMMON.healFires.get() && !level.isClientSide()) {
            FireHandler.handleFire(level, blockPos);
        }
    }

    @Inject(method = "checkBurnOut", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/Level;removeBlock(Lnet/minecraft/core/BlockPos;Z)Z"))
    public void reclaim$removeBlockCheckBurnOut(Level level, BlockPos blockPos, int chance,
        RandomSource random,
        int age, CallbackInfo ci) {
        if (ReclaimConfig.COMMON.healFires.get() && !level.isClientSide()) {
            FireHandler.handleFire(level, blockPos);
        }
    }
}
