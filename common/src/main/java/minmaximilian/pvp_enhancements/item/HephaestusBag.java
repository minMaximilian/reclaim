package minmaximilian.pvp_enhancements.item;

import java.util.Objects;

import li.cil.scannable.client.renderer.ScannerRenderer;
import minmaximilian.pvp_enhancements.regen.handlers.HandleLevelTick;
import minmaximilian.pvp_enhancements.regen.util.ChunkPosUtils;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

public class HephaestusBag extends Item {
    boolean charged;

    public HephaestusBag(Properties properties) {
        super(new Properties().stacksTo(1).fireResistant().rarity(Rarity.RARE).tab(CreativeModeTab.TAB_SEARCH));
    }

    @Override
    public InteractionResultHolder<ItemStack> use(final Level level, final Player player, final InteractionHand hand) {
        final ItemStack stack = player.getItemInHand(hand);
        if (level.isClientSide() && hand != InteractionHand.MAIN_HAND) return InteractionResultHolder.fail(stack);

        Vec3 lastScanCenter = Objects.requireNonNull(player.position());

        ScannerRenderer.INSTANCE.ping(lastScanCenter);

        level.playSound(null, player.getOnPos().above(10), SoundEvents.LIGHTNING_BOLT_THUNDER, SoundSource.MASTER, 1.0F, 0.8F + 0.4F * level.getRandom().nextFloat());

        HandleLevelTick.healChunks(ChunkPosUtils.getAdjacentChunkPositions(new ChunkPos(player.getOnPos())));

        return InteractionResultHolder.success(stack);
    }
}
