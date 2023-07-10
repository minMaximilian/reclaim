package minmaximilian.pvp_enhancements.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;

import minmaximilian.pvp_enhancements.event.BlockPlaceEvents;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.item.context.UseOnContext;

@Mixin(BlockItem.class)
public class BlockPlaceMixin {

    @ModifyExpressionValue(
        method = "useOn",
        at = @At(
            value = "INVOKE",
            target = "Lnet/minecraft/world/item/BlockItem;place(Lnet/minecraft/world/item/context/BlockPlaceContext;)Lnet/minecraft/world/InteractionResult;"
        )
    )
    private InteractionResult maxs_pvp_enhancements$afterPlace(InteractionResult placeResult, UseOnContext context) {
        if (placeResult.consumesAction()) {
            BlockPlaceEvents.PLACE.invoker().onPlace(new BlockPlaceContext(context));
        }
        return placeResult;
    }
}
