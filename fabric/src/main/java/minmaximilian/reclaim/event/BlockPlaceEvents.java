package minmaximilian.reclaim.event;

import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.minecraft.world.item.context.BlockPlaceContext;

public class BlockPlaceEvents {

    public static Event<Place> PLACE = EventFactory.createArrayBacked(Place.class,
        callbacks -> ((blockPlaceContext) -> {
            for (Place event : callbacks) {
                event.onPlace(blockPlaceContext);
            }
        }));

    @FunctionalInterface
    public interface Place {

        void onPlace(BlockPlaceContext blockPlaceContext);
    }
}
