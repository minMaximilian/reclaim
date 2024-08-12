package minmaximilian.reclaim.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

import com.llamalad7.mixinextras.injector.WrapWithCondition;

import minmaximilian.reclaim.event.EntityEvents;
import net.minecraft.world.entity.item.ItemEntity;

@Mixin(ItemEntity.class)
public abstract class ItemEntityMixin implements PickupDelayMixin {
    @WrapWithCondition(method = "tick", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/item/ItemEntity;discard()V"))
    public boolean reclaim$aboutToDespawn(ItemEntity itemEntity) {
        return !EntityEvents.ITEM_DESPAWN_EVENT.invoker().onEntityAboutToDespawn(itemEntity, getPickupDelay());
    }
}
