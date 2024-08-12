package minmaximilian.reclaim.registry;

import static minmaximilian.reclaim.Reclaim.REGISTRATE;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

import com.simibubi.create.content.processing.sequenced.SequencedAssemblyItem;
import com.tterrag.registrate.util.entry.ItemProviderEntry;
import com.tterrag.registrate.util.entry.RegistryEntry;

import dev.architectury.injectables.annotations.ExpectPlatform;
import it.unimi.dsi.fastutil.objects.Reference2ObjectOpenHashMap;
import it.unimi.dsi.fastutil.objects.Reference2ReferenceOpenHashMap;
import it.unimi.dsi.fastutil.objects.ReferenceArrayList;
import it.unimi.dsi.fastutil.objects.ReferenceLinkedOpenHashSet;
import it.unimi.dsi.fastutil.objects.ReferenceOpenHashSet;
import minmaximilian.reclaim.multiloader.Env;
import net.minecraft.client.Minecraft;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTab.TabVisibility;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;

public class ReclaimCreativeModeTab {

    @ExpectPlatform
    public static CreativeModeTab getBaseTab() {
        throw new AssertionError();
    }

    @ExpectPlatform
    public static ResourceKey<CreativeModeTab> getBaseTabKey() {
        throw new AssertionError();
    }

    public static void register() {
    }

    public enum Tabs {
        MAIN(ReclaimCreativeModeTab::getBaseTabKey);

        private final Supplier<ResourceKey<CreativeModeTab>> keySupplier;

        Tabs(Supplier<ResourceKey<CreativeModeTab>> keySupplier) {
            this.keySupplier = keySupplier;
        }

        public ResourceKey<CreativeModeTab> getKey() {
            return keySupplier.get();
        }
    }

    public static final class RegistrateDisplayItemsGenerator implements
        CreativeModeTab.DisplayItemsGenerator {

        private final Tabs tab;

        public RegistrateDisplayItemsGenerator(Tabs tab) {
            this.tab = tab;
        }

        private static Predicate<Item> makeExclusionPredicate() {
            Set<Item> exclusions = new ReferenceOpenHashSet<>();

            List<ItemProviderEntry<?>> simpleExclusions = List.of(
                //AllBlocks.REFINED_RADIANCE_CASING // just as an example
            );

            for (ItemProviderEntry<?> entry : simpleExclusions) {
                exclusions.add(entry.asItem());
            }

            return (item) -> exclusions.contains(item) || item instanceof SequencedAssemblyItem;
        }

        private static Function<Item, ItemStack> makeStackFunc() {
            Map<Item, Function<Item, ItemStack>> factories = new Reference2ReferenceOpenHashMap<>();

            Map<ItemProviderEntry<?>, Function<Item, ItemStack>> simpleFactories = Map.of(
                /*AllItems.COPPER_BACKTANK, item -> {
                    ItemStack stack = new ItemStack(item);
                    stack.getOrCreateTag().putInt("Air", BacktankUtil.maxAirWithoutEnchants());
                    return stack;
                },
                AllItems.NETHERITE_BACKTANK, item -> {
                    ItemStack stack = new ItemStack(item);
                    stack.getOrCreateTag().putInt("Air", BacktankUtil.maxAirWithoutEnchants());
                    return stack;
                }*/
            );

            simpleFactories.forEach((entry, factory) -> {
                factories.put(entry.asItem(), factory);
            });

            return item -> {
                Function<Item, ItemStack> factory = factories.get(item);
                if (factory != null) {
                    return factory.apply(item);
                }
                return new ItemStack(item);
            };
        }

        private static Function<Item, TabVisibility> makeVisibilityFunc() {
            Map<Item, TabVisibility> visibilities = new Reference2ObjectOpenHashMap<>();

            Map<ItemProviderEntry<?>, TabVisibility> simpleVisibilities = Map.of(
                //AllItems.BLAZE_CAKE_BASE, TabVisibility.SEARCH_TAB_ONLY
            );

            simpleVisibilities.forEach((entry, factory) -> {
                visibilities.put(entry.asItem(), factory);
            });

            return item -> {
                TabVisibility visibility = visibilities.get(item);
                if (visibility != null) {
                    return visibility;
                }
                return TabVisibility.PARENT_AND_SEARCH_TABS;
            };
        }

        private static void outputAll(CreativeModeTab.Output output, List<Item> items,
            Function<Item, ItemStack> stackFunc, Function<Item, TabVisibility> visibilityFunc) {
            for (Item item : items) {
                output.accept(stackFunc.apply(item), visibilityFunc.apply(item));
            }
        }

        @ExpectPlatform
        private static boolean isInCreativeTab(RegistryEntry<?> entry, ResourceKey<CreativeModeTab> tab) {
            throw new AssertionError();
        }


        @Override
        public void accept(CreativeModeTab.ItemDisplayParameters pParameters,
            CreativeModeTab.Output output) {
            Function<Item, ItemStack> stackFunc = makeStackFunc();
            Function<Item, TabVisibility> visibilityFunc = makeVisibilityFunc();
            ResourceKey<CreativeModeTab> tab = this.tab.getKey();

            List<Item> items = new LinkedList<>();
            Predicate<Item> is3d = Env.unsafeRunForDist(
                () -> () -> item -> Minecraft.getInstance().getItemRenderer()
                    .getModel(new ItemStack(item), null, null, 0).isGui3d(),
                () -> () -> item -> false // don't crash servers
            );
            items.addAll(collectItems(tab, is3d, true));
            items.addAll(collectBlocks(tab));
            items.addAll(collectItems(tab, is3d, false));

            outputAll(output, items, stackFunc, visibilityFunc);
        }

        private List<Item> collectBlocks(ResourceKey<CreativeModeTab> tab) {
            List<Item> items = new ReferenceArrayList<>();
            for (RegistryEntry<Block> entry : REGISTRATE.getAll(Registries.BLOCK)) {
                if (isInCreativeTab(entry, tab)) {
                    continue;
                }
                Item item = entry.get()
                    .asItem();
                if (item == Items.AIR) {
                    continue;
                }
                items.add(item);
            }
            items = new ReferenceArrayList<>(new ReferenceLinkedOpenHashSet<>(items));
            return items;
        }

        private List<Item> collectItems(ResourceKey<CreativeModeTab> tab, Predicate<Item> is3d,
            boolean special) {
            List<Item> items = new ReferenceArrayList<>();

            for (RegistryEntry<Item> entry : REGISTRATE.getAll(Registries.ITEM)) {
                if (isInCreativeTab(entry, tab)) {
                    continue;
                }
                Item item = entry.get();
                if (item instanceof BlockItem) {
                    continue;
                }
                if (is3d.test(item) != special) {
                    continue;
                }
                items.add(item);
            }
            return items;
        }
    }

    public record TabInfo(ResourceKey<CreativeModeTab> key, CreativeModeTab tab) {
    }
}
