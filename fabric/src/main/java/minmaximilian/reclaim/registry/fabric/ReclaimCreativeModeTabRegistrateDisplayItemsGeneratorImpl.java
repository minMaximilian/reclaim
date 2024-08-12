package minmaximilian.reclaim.registry.fabric;

import com.tterrag.registrate.util.entry.RegistryEntry;

import minmaximilian.reclaim.Reclaim;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.CreativeModeTab;

public class ReclaimCreativeModeTabRegistrateDisplayItemsGeneratorImpl {

    public static boolean isInCreativeTab(RegistryEntry<?> entry, ResourceKey<CreativeModeTab> tab) {
        return Reclaim.REGISTRATE.isInCreativeTab(entry, tab);
    }
}
