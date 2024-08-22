package akai.cheers;

import akai.cheers.twitch.TwitchHandler;
import akai.cheers.twitch.TwitchListener;
import com.mojang.brigadier.Command;
import com.mojang.brigadier.CommandDispatcher;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.text.Text;

public final class CheersCommand {
    public static void register(CommandDispatcher<ServerCommandSource> dispatcher){
        dispatcher.register(
                CommandManager.literal("cheers4j")
                        .requires(source -> source.hasPermissionLevel(2)) // Must be a game master to use the command. Command will not show up in tab completion or execute to non operators or any operator that is permission level 1.
                        .then(
                                CommandManager.literal("reload").executes(
                                        commandContext -> reload(commandContext.getSource())
                                )
                        )
                        .then(
                                CommandManager.literal("start").executes(
                                        commandContext -> setStopped(false, commandContext.getSource())
                                )
                        )
                        .then(
                                CommandManager.literal("stop").executes(
                                        commandContext -> setStopped(true, commandContext.getSource())
                                )
                        )
                        .then(
                                CommandManager.literal("connect").executes(
                                        commandContext -> setConnection(true)
                                )
                        )
                        .then(
                                CommandManager.literal("disconnect").executes(
                                        commandContext -> setConnection(false)
                                )
                        )
        );
    }

    private static int reload(ServerCommandSource source) {
        ConfigUtil.load();
        source.sendFeedback(Text.literal("Cheers4J Configs Reloaded!"),true);
        return Command.SINGLE_SUCCESS; // Success
    }

    private static int setStopped(boolean stopped, ServerCommandSource source) {
        if(TwitchListener.stopped != stopped) {
            TwitchListener.stopped = stopped;
            source.sendFeedback(Text.literal("Twitch Listeners just " + (stopped ? "stopped" : "started")),true);
        }
        return Command.SINGLE_SUCCESS; // Success
    }

    private static int setConnection(boolean connected) {
        if(connected)
            TwitchHandler.connect();
        else
            TwitchHandler.disconnect();

        return Command.SINGLE_SUCCESS; // Success
    }
}