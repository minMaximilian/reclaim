package minmaximilian.pvp_enhancements.fabric.event;

import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LightningBolt;

public class EntityEvents {
    public static final Event<LightningStrike> STRUCK_BY_LIGHTNING = EventFactory.createArrayBacked(LightningStrike.class, callbacks -> (entity, lightningBolt) -> {
        for (LightningStrike callback : callbacks)
            if (callback.onEntityStruckByLightning(entity, lightningBolt))
                return true;
        return false;

    });

    @FunctionalInterface
    public interface LightningStrike {
        boolean onEntityStruckByLightning(Entity entity, LightningBolt bolt);
    }
}
