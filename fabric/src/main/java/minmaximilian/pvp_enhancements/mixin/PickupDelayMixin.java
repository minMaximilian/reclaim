package minmaximilian.pvp_enhancements.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

import net.minecraft.world.entity.item.ItemEntity;

@Mixin(ItemEntity.class)
public interface PickupDelayMixin {
    @Accessor("pickupDelay")
    int getPickupDelay();
}
