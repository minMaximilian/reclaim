package minmaximilian.reclaim.registry.fabric;

import java.util.function.Supplier;

import minmaximilian.reclaim.Reclaim;
import minmaximilian.reclaim.item.ReclaimItems;
import minmaximilian.reclaim.registry.ReclaimCreativeModeTab.RegistrateDisplayItemsGenerator;
import minmaximilian.reclaim.registry.ReclaimCreativeModeTab.TabInfo;
import minmaximilian.reclaim.registry.ReclaimCreativeModeTab.Tabs;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;

public class ReclaimCreativeModeTabImpl {

    private static final TabInfo MAIN_TAB = register("main",
        () -> FabricItemGroup.builder()
            .title(Component.literal("Reclaim"))
            .icon(ReclaimItems.HEPHAESTUS_BAG::asStack)
            .displayItems(new RegistrateDisplayItemsGenerator(Tabs.MAIN))
            .build());

    public static CreativeModeTab getBaseTab() {
        return MAIN_TAB.tab();
    }

    public static ResourceKey<CreativeModeTab> getBaseTabKey() {
        return MAIN_TAB.key();
    }

    private static TabInfo register(String name, Supplier<CreativeModeTab> supplier) {
        ResourceLocation id = Reclaim.asResource(name);
        ResourceKey<CreativeModeTab> key = ResourceKey.create(Registries.CREATIVE_MODE_TAB, id);
        CreativeModeTab tab = supplier.get();
        Registry.register(BuiltInRegistries.CREATIVE_MODE_TAB, key, tab);
        return new TabInfo(key, tab);
    }
}
