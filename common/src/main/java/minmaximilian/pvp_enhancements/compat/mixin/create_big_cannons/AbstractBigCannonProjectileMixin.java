package minmaximilian.pvp_enhancements.compat.mixin.create_big_cannons;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import minmaximilian.pvp_enhancements.compat.handlers.HandleCreateBigCannons;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import rbasamoyai.createbigcannons.munitions.big_cannon.AbstractBigCannonProjectile;

@Mixin(AbstractBigCannonProjectile.class)
public abstract class AbstractBigCannonProjectileMixin extends Entity {

    public AbstractBigCannonProjectileMixin(EntityType<?> entityType, Level level) {
        super(entityType, level);
    }

    @Inject(method = "onDestroyBlock", at = @At("HEAD"))
    public void maxs_pvp_enhancements$penetrationDestroyed(BlockState state, BlockHitResult result, CallbackInfo ci) {
        if (!level.isClientSide()) {
            HandleCreateBigCannons.handlePenetration(getLevel(), state, result);
        }
    }
}
