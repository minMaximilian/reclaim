package minmaximilian.pvp_enhancements.item;

import java.util.Objects;

import li.cil.scannable.client.renderer.ScannerRenderer;
import minmaximilian.pvp_enhancements.regen.handlers.HandleLevelTick;
import minmaximilian.pvp_enhancements.regen.util.ChunkPosUtils;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;


public class HephaestusBag extends Item {
    public static final String NBT_KEY_CHARGED = "Charged";

    public HephaestusBag(Properties properties) {
        super(properties);
    }

    public boolean isCharged(ItemStack stack) {
        return stack.getOrCreateTag().getBoolean(NBT_KEY_CHARGED);
    }

    public void setCharged(ItemStack stack, boolean charged) {
        stack.getOrCreateTag().putBoolean(NBT_KEY_CHARGED, charged);
    }

    @Override
    public boolean isFoil(ItemStack stack) {
        return isCharged(stack);
    }

    @Override
    public boolean canBeHurtBy(DamageSource damageSource) {
        return false;
    }

    @Override
    public InteractionResultHolder<ItemStack> use(final Level level, final Player player, final InteractionHand hand) {
        final ItemStack stack = player.getItemInHand(hand);
        if (level.isClientSide() && hand != InteractionHand.MAIN_HAND) return InteractionResultHolder.fail(stack);

        if (!isCharged(stack)) return InteractionResultHolder.fail(stack);


        Vec3 lastScanCenter = Objects.requireNonNull(player.position());

        ScannerRenderer.INSTANCE.ping(lastScanCenter);

        level.playSound(null, player.getOnPos().above(10), SoundEvents.LIGHTNING_BOLT_THUNDER, SoundSource.MASTER, 1.0F, 0.8F + 0.4F * level.getRandom().nextFloat());

        HandleLevelTick.healChunks(ChunkPosUtils.getAdjacentChunkPositions(new ChunkPos(player.getOnPos())));

        if (!player.isCreative()) {
            setCharged(stack, false);
        }


        return InteractionResultHolder.success(stack);
    }
}
