package minmaximilian.reclaim.compat.mixin.create_big_cannons;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import minmaximilian.reclaim.compat.handlers.HandleCreateBigCannons;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import rbasamoyai.createbigcannons.base.PartialBlockDamageManager;

@Mixin(PartialBlockDamageManager.class)
public class PartialBlockDamageManagerMixin {

    @Inject(method = "damageBlock", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/Level;destroyBlock(Lnet/minecraft/core/BlockPos;Z)Z"))
    public void reclaim$partialDamageDestroyed(BlockPos pos, int added, BlockState state, Level level,
        CallbackInfo ci) {
        HandleCreateBigCannons.handlePenetration(level, pos);
    }
}
