package minmaximilian.pvp_enhancements.registry.fabric;

import java.util.function.Supplier;

import minmaximilian.pvp_enhancements.PvPEnhancements;
import minmaximilian.pvp_enhancements.item.PvPEnhancementsItems;
import minmaximilian.pvp_enhancements.registry.PvPEnhancementsCreativeModeTab.RegistrateDisplayItemsGenerator;
import minmaximilian.pvp_enhancements.registry.PvPEnhancementsCreativeModeTab.TabInfo;
import minmaximilian.pvp_enhancements.registry.PvPEnhancementsCreativeModeTab.Tabs;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;

public class PvPEnhancementsCreativeModeTabImpl {

    private static final TabInfo MAIN_TAB = register("main",
        () -> FabricItemGroup.builder()
            .title(Component.literal("Max's PvP Enhancements"))
            .icon(PvPEnhancementsItems.HEPHAESTUS_BAG::asStack)
            .displayItems(new RegistrateDisplayItemsGenerator(Tabs.MAIN))
            .build());

    public static CreativeModeTab getBaseTab() {
        return MAIN_TAB.tab();
    }

    public static ResourceKey<CreativeModeTab> getBaseTabKey() {
        return MAIN_TAB.key();
    }

    private static TabInfo register(String name, Supplier<CreativeModeTab> supplier) {
        ResourceLocation id = PvPEnhancements.asResource(name);
        ResourceKey<CreativeModeTab> key = ResourceKey.create(Registries.CREATIVE_MODE_TAB, id);
        CreativeModeTab tab = supplier.get();
        Registry.register(BuiltInRegistries.CREATIVE_MODE_TAB, key, tab);
        return new TabInfo(key, tab);
    }
}
