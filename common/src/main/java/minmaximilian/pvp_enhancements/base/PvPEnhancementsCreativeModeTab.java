package minmaximilian.pvp_enhancements.base;

import java.util.Arrays;

import minmaximilian.pvp_enhancements.IndexPlatform;
import minmaximilian.pvp_enhancements.PvPEnhancements;
import minmaximilian.pvp_enhancements.item.Items;
import net.minecraft.core.NonNullList;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

public class PvPEnhancementsCreativeModeTab {
    public static final CreativeModeTab GROUP = new CreativeModeTab(IndexPlatform.getModGroupId(), PvPEnhancements.MOD_ID) {
        @Override
        public ItemStack makeIcon() {
            return Items.HEPHAESTUS_BAG.asStack();
        }

        @Override
        public void fillItemList(NonNullList<ItemStack> list) {
            list.addAll(Arrays.asList(
                Items.HEPHAESTUS_BAG.asStack()));
        }
    };

    public static void register() {
        PvPEnhancements.REGISTRATE.creativeModeTab(() -> GROUP, "Max's PvP Enhancements");
    }
}
