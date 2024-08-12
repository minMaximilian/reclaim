package minmaximilian.reclaim.regen.handlers;

import minmaximilian.reclaim.item.HephaestusBag;
import minmaximilian.reclaim.item.ReclaimItems;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LightningBolt;
import net.minecraft.world.entity.item.ItemEntity;

public class HandleLightningStrike {

    public static boolean handleLightningStrike(Entity entity, LightningBolt lightning) {
        if (entity instanceof ItemEntity item
            && item.getItem().getItem() == ReclaimItems.HEPHAESTUS_BAG.get()) {
            item.getItem().getOrCreateTag().putBoolean(HephaestusBag.NBT_KEY_CHARGED, true);
            return true;
        }
        return false;
    }
}
