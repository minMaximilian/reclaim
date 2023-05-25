package minmaximilian.pvp_enhancements.fabric.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;

import minmaximilian.pvp_enhancements.fabric.event.EntityEvents;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LightningBolt;

@Mixin(LightningBolt.class)
public class LightningBoltMixin {
    @Inject(method = "tick", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/Entity;thunderHit(Lnet/minecraft/server/level/ServerLevel;Lnet/minecraft/world/entity/LightningBolt;)V"))
    private void shouldStrikeEntity(Entity entity, ServerLevel level, LightningBolt lightningBolt) {
        EntityEvents.STRUCK_BY_LIGHTNING.invoker().onEntityStruckByLightning(entity, lightningBolt);
    }
}
