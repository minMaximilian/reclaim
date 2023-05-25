package minmaximilian.pvp_enhancements.fabric.mixin;

import java.util.List;
import java.util.Set;

import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

import minmaximilian.pvp_enhancements.fabric.event.ExplosionEvents;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.Level;

@Mixin(Explosion.class)
public abstract class ExplosionMixin {

    @Shadow
    @Final
    private Level level;

    @Inject(method = "explode", at = @At(value = "NEW", target = "net/minecraft/world/phys/Vec3", ordinal = 1), locals = LocalCapture.CAPTURE_FAILHARD)
    public void onExplode(CallbackInfo ci, Set<BlockPos> blocks, int i, float j, int k, int l, int d, int q, int e, int r, List<Entity> list) {
        ExplosionEvents.DETONATE.invoker().onDetonate(this.level, (Explosion) (Object) this, list, j);
    }
}
