package minmaximilian.reclaim.forge;

import static minmaximilian.reclaim.item.ReclaimItems.HEPHAESTUS_BAG;

import minmaximilian.reclaim.Reclaim;
import minmaximilian.reclaim.registry.ReclaimCreativeModeTab.RegistrateDisplayItemsGenerator;
import minmaximilian.reclaim.registry.ReclaimCreativeModeTab.Tabs;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class ReclaimCreativeModeTab {

    private static final DeferredRegister<CreativeModeTab> TAB_REGISTER =
        DeferredRegister.create(Registries.CREATIVE_MODE_TAB, Reclaim.MOD_ID);
    public static final RegistryObject<CreativeModeTab> MAIN_TAB = TAB_REGISTER.register("main",
        () -> CreativeModeTab.builder()
            .title(Component.literal("Reclaim"))
            .withTabsBefore(CreativeModeTabs.SPAWN_EGGS)
            .icon(HEPHAESTUS_BAG::asStack)
            .displayItems(new RegistrateDisplayItemsGenerator(Tabs.MAIN))
            .build());
}