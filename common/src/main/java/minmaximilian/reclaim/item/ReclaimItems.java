package minmaximilian.reclaim.item;

import static minmaximilian.reclaim.Reclaim.REGISTRATE;

import com.tterrag.registrate.util.entry.ItemEntry;

import net.minecraft.world.item.Rarity;

public class ReclaimItems {

    public static final ItemEntry<HephaestusBag> HEPHAESTUS_BAG = REGISTRATE.item("hephaestus_bag", HephaestusBag::new)
        .properties(p -> {
            p.stacksTo(1);
            p.rarity(Rarity.EPIC);
            return p;
        })
        .lang("Hephaestus's Bag")
        .register();

    @SuppressWarnings("EmptyMethod")
    public static void register() {
    }
}
