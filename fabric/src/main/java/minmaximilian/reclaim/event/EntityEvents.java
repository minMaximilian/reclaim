package minmaximilian.reclaim.event;

import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LightningBolt;
import net.minecraft.world.entity.item.ItemEntity;

public class EntityEvents {

    public static final Event<LightningStrike> STRUCK_BY_LIGHTNING = EventFactory.createArrayBacked(
        LightningStrike.class, callbacks -> (entity, lightningBolt) -> {
            for (LightningStrike callback : callbacks) {
                if (callback.onEntityStruckByLightning(entity, lightningBolt)) {
                    return true;
                }
            }
            return false;

        });
    public static final Event<ItemDespawn> ITEM_DESPAWN_EVENT = EventFactory.createArrayBacked(ItemDespawn.class,
        callbacks -> (itemEntity, pickupDelay) -> {
            for (ItemDespawn callback : callbacks) {
                if (callback.onEntityAboutToDespawn(itemEntity, pickupDelay)) {
                    return true;
                }
            }
            return false;

        });

    @FunctionalInterface
    public interface LightningStrike {

        boolean onEntityStruckByLightning(Entity entity, LightningBolt bolt);
    }

    @FunctionalInterface
    public interface ItemDespawn {

        boolean onEntityAboutToDespawn(ItemEntity itemEntity, int pickupDelay);
    }
}
