package minmaximilian.pvp_enhancements.registry.forge;

import static minmaximilian.pvp_enhancements.item.PvPEnhancementsItems.HEPHAESTUS_BAG;

import minmaximilian.pvp_enhancements.PvPEnhancements;
import minmaximilian.pvp_enhancements.registry.PvPEnhancementsCreativeModeTab.RegistrateDisplayItemsGenerator;
import minmaximilian.pvp_enhancements.registry.PvPEnhancementsCreativeModeTab.Tabs;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class PvPEnhancementsCreativeModeTabImpl {

    private static final DeferredRegister<CreativeModeTab> TAB_REGISTER =
        DeferredRegister.create(Registries.CREATIVE_MODE_TAB, PvPEnhancements.MOD_ID);

    private static final RegistryObject<CreativeModeTab> MAIN_TAB = TAB_REGISTER.register("main",
        () -> CreativeModeTab.builder()
            .title(Component.literal("Max's PvP Enhancements"))
            .withTabsBefore(CreativeModeTabs.SPAWN_EGGS)
            .icon(HEPHAESTUS_BAG::asStack)
            .displayItems(new RegistrateDisplayItemsGenerator(Tabs.MAIN))
            .build());

    public static CreativeModeTab getBaseTab() {
        return MAIN_TAB.get();
    }

    public static ResourceKey<CreativeModeTab> getBaseTabKey() {
        return MAIN_TAB.getKey();
    }
}