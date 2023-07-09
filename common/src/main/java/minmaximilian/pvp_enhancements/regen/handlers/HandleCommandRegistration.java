package minmaximilian.pvp_enhancements.regen.handlers;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;

import minmaximilian.pvp_enhancements.PvPEnhancements;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.network.chat.TranslatableComponent;

public class HandleCommandRegistration {

    public static void registerCommands(CommandDispatcher<CommandSourceStack> dispatcher) {
        dispatcher.register(Commands.literal("healChunks")
            .requires(player ->
                player.hasPermission(2)
            )
            .executes(HandleCommandRegistration::forceHealAll));
    }

    private static int forceHealAll(CommandContext<CommandSourceStack> context) throws CommandSyntaxException {
        context.getSource()
            .getPlayerOrException()
            .displayClientMessage(new TranslatableComponent(PvPEnhancements.MOD_ID + ".commands.healingChunks"), false);
        HandleLevelTick.healChunks();
        return 1;
    }
}
