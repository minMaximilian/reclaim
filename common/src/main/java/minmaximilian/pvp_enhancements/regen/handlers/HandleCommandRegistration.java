package minmaximilian.pvp_enhancements.regen.handlers;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.context.CommandContext;

import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.network.chat.Component;

public class HandleCommandRegistration {

    public static void registerCommands(CommandDispatcher<CommandSourceStack> dispatcher) {
        dispatcher.register(Commands.literal("healChunks")
            .requires(player ->
                player.hasPermission(2)
            )
            .executes(HandleCommandRegistration::forceHealAll));
    }

    private static int forceHealAll(CommandContext<CommandSourceStack> context) {
        context.getSource().getPlayer().displayClientMessage(Component.translatable("Healing Chunks"), false);
        HandleLevelTick.healChunks();
        return 1;
    }
}
