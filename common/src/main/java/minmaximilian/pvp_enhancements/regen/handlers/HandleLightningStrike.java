package minmaximilian.pvp_enhancements.regen.handlers;

import minmaximilian.pvp_enhancements.item.Items;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LightningBolt;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class HandleLightningStrike {
    public static void handleLightningStrike(Entity entity, LightningBolt lightning) {
        if (entity instanceof ItemEntity item && item.getItem().getItem() == Items.HEPHAESTUS_BAG.get()) {
            Level level = lightning.getLevel();
            level.addFreshEntity(new ItemEntity(level, item.getX(), item.getY(), item.getZ(), new ItemStack(Items.HEPHAESTUS_BAG.get(), 1), 0.0, 0.0, 0.0));
        }
    }
}
