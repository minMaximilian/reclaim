package minmaximilian.pvp_enhancements.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

import com.llamalad7.mixinextras.injector.WrapWithCondition;

import minmaximilian.pvp_enhancements.event.EntityEvents;
import net.minecraft.world.entity.item.ItemEntity;

@Mixin(ItemEntity.class)
public abstract class ItemEntityMixin {

    @WrapWithCondition(method = "tick", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/item/ItemEntity;discard()V"))
    public boolean maxs_pvp_enhancements$aboutToDespawn(ItemEntity itemEntity) {
        return !EntityEvents.ITEM_DESPAWN_EVENT.invoker().onEntityAboutToDespawn(itemEntity);
    }
}
