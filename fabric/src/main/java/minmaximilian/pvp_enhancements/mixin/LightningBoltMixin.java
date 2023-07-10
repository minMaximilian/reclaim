package minmaximilian.pvp_enhancements.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

import com.llamalad7.mixinextras.injector.WrapWithCondition;

import minmaximilian.pvp_enhancements.event.EntityEvents;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LightningBolt;

@Mixin(LightningBolt.class)
public class LightningBoltMixin {

    @WrapWithCondition(method = "tick", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/Entity;thunderHit(Lnet/minecraft/server/level/ServerLevel;Lnet/minecraft/world/entity/LightningBolt;)V"))
    private boolean maxs_pvp_enhancements$shouldStrikeEntity(Entity entity, ServerLevel level,
        LightningBolt lightningBolt) {
        return !EntityEvents.STRUCK_BY_LIGHTNING.invoker().onEntityStruckByLightning(entity, lightningBolt);
    }
}
