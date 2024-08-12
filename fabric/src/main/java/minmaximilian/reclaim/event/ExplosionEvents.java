package minmaximilian.reclaim.event;

import java.util.List;

import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.Level;

public class ExplosionEvents {

    public static Event<Detonate> DETONATE = EventFactory.createArrayBacked(Detonate.class,
        callbacks -> ((world, explosion, list, diameter) -> {
            for (Detonate event : callbacks) {
                event.onDetonate(world, explosion, list, diameter);
            }
        }));

    @FunctionalInterface
    public interface Detonate {

        void onDetonate(Level world, Explosion explosion, List<Entity> list, double diameter);
    }
}
