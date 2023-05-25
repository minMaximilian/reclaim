package minmaximilian.pvp_enhancements.fabric.event;

import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LightningBolt;

public class EntityEvents {
    public static final Event<LightningStrike> STRUCK_BY_LIGHTNING = EventFactory.createArrayBacked(LightningStrike.class, callbacks -> (entity, lightningBolt) -> {
        for (LightningStrike callback : callbacks)
            callback.onEntityStruckByLightning(entity, lightningBolt);

    });

    @FunctionalInterface
    public interface LightningStrike {
        void onEntityStruckByLightning(Entity entity, LightningBolt bolt);
    }
}
