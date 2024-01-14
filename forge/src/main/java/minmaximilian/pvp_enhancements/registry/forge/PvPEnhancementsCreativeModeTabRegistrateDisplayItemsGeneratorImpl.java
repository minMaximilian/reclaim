package minmaximilian.pvp_enhancements.registry.forge;

import com.tterrag.registrate.util.entry.RegistryEntry;

import minmaximilian.pvp_enhancements.PvPEnhancements;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.CreativeModeTab;

public class PvPEnhancementsCreativeModeTabRegistrateDisplayItemsGeneratorImpl {

    public static boolean isInCreativeTab(RegistryEntry<?> entry, ResourceKey<CreativeModeTab> tab) {
        return PvPEnhancements.REGISTRATE.isInCreativeTab(entry, tab);
    }
}
