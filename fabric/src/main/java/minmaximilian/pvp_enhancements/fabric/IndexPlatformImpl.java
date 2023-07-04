package minmaximilian.pvp_enhancements.fabric;

import minmaximilian.pvp_enhancements.data.PvPRegistrate;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Items;

public class IndexPlatformImpl {
    public static final ResourceLocation ERROR_ID = new ResourceLocation("if_you_see_this", "something_went_wrong");

    public static int getModGroupId() {
        return FabricItemGroupBuilder.build(ERROR_ID, Items.AIR::getDefaultInstance).getId();
    }

    public static boolean isModLoaded(String id) {
        return FabricLoader.getInstance().isModLoaded(id);
    }

    public static PvPRegistrate createRegistrate(String modid) {
        return new PvPRegistrate(modid);
    }
}
