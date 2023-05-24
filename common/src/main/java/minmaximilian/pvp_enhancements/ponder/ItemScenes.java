package minmaximilian.pvp_enhancements.ponder;

import com.simibubi.create.foundation.ponder.SceneBuilder;
import com.simibubi.create.foundation.ponder.SceneBuildingUtil;
import com.simibubi.create.foundation.ponder.Selection;

import minmaximilian.pvp_enhancements.item.Items;
import net.minecraft.core.Direction;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.phys.Vec3;

public class ItemScenes {
    public static void chargingHephaestusBag(SceneBuilder scene, SceneBuildingUtil util) {
        scene.title("items/hephaestus_bag", "Hephaestus' Bag");
        scene.configureBasePlate(0, 0, 5);
        scene.showBasePlate();

        scene.idle(20);

        scene.world.createItemEntity(new Vec3(1, 1.5, 2), new Vec3(0.5, 0, 0), new ItemStack(Items.HEPHAESTUS_BAG.get(), 1));

        scene.idle(20);

        scene.overlay.showText(80)
            .text("Drop the Hephaestus' Bag on the floor during a thunderstorm.")
            .pointAt(util.vector.blockSurface(util.grid.at(2, 1, 2), Direction.EAST));

        scene.idle(100);

        Selection lightningRod = util.select.position(2, 2, 2);

        scene.world.showSection(lightningRod, Direction.DOWN);

        scene.overlay.showText(80)
            .text("A lightning rod can expedite the process.")
            .pointAt(util.vector.blockSurface(util.grid.at(2, 2, 2), Direction.EAST));

        scene.idle(100);

        scene.markAsFinished();
    }
}
