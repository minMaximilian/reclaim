package minmaximilian.pvp_enhancements.item;

import static minmaximilian.pvp_enhancements.PvPEnhancements.REGISTRATE;

import com.tterrag.registrate.util.entry.ItemEntry;

import minmaximilian.pvp_enhancements.base.PvPEnhancementsCreativeModeTab;

public class Items {
    public static final ItemEntry<HephaestusBag> HEPHAESTUS_BAG = REGISTRATE.item("hephaestus_bag", HephaestusBag::new)
        .register();

    static {
        REGISTRATE.creativeModeTab(() -> PvPEnhancementsCreativeModeTab.GROUP);
    }

    public static void register() {
    }
}
