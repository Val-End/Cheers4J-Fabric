package akai.cheers.twitch;

import akai.cheers.ConfigUtil;
import akai.cheers.twitch.requests.RequestHandler;
import akai.cheers.types.message.Event;
import com.google.gson.Gson;

import java.net.http.WebSocket;
import java.net.http.WebSocket.Listener;
import java.util.Objects;
import java.util.concurrent.CompletionStage;

import akai.cheers.Cheers4J;
import akai.cheers.types.config.Channel;
import akai.cheers.types.message.Message;
import com.mojang.brigadier.ParseResults;
import net.minecraft.command.CommandSource;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Style;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;

public class TwitchListener implements Listener {
    private static final Gson gson = new Gson();
    private static MinecraftServer server = null;
    private final Channel channel;
    public static boolean stopped = true;

    public TwitchListener(Channel channel) {
        this.channel = channel;
    }

    @Override
    public void onOpen(WebSocket webSocket) {
        Cheers4J.LOGGER.info("{} websocket opened", channel.getUser());
        Listener.super.onOpen(webSocket);
    }

    @Override
    public CompletionStage<?> onText(WebSocket webSocket, CharSequence data, boolean last) {
        handleMessage(data.toString());
        return Listener.super.onText(webSocket, data, last);
    }

    @Override
    public CompletionStage<?> onClose(WebSocket webSocket, int statusCode, String reason) {
        Cheers4J.LOGGER.warn("{} websocket closed ({}): {}", channel.getUser(), statusCode, reason);
        RequestHandler.refreshToken(channel.getUser(), channel.getRefreshToken());
        return Listener.super.onClose(webSocket, statusCode, reason);
    }

    @Override
    public void onError(WebSocket webSocket, Throwable error) {
        Cheers4J.LOGGER.error(channel.getUser() + " socket got an error.", error);
        RequestHandler.refreshToken(channel.getUser(), channel.getRefreshToken());
        Listener.super.onError(webSocket, error);
    }

    public static void setServer(MinecraftServer server) {
        TwitchListener.server = server;
    }

    private void handleMessage(String content) {
        Message message = gson.fromJson(content, Message.class);
        switch (message.getMetadata().getMessageType()){
            case "session_welcome":
                RequestHandler.subscribe(channel.getUser(), channel.getAccessToken(), message.getPayload().getSession().getID());
                Cheers4J.LOGGER.info("Welcome to Twitch EventSub: {}", channel.getUser());
                break;
            case "notification":
                if(server != null && !stopped){
                    Event event = message.getPayload().getEvent();
                    String command = ConfigUtil.getEvent(event.getBits());
                    if(Objects.equals(command, ""))
                        break;

                    for(ServerPlayerEntity player : server.getPlayerManager().getPlayerList()){
                        player.sendMessage(Text.literal("").append(
                                Text.literal(event.getUserName()).setStyle(Style.EMPTY.withColor(Formatting.GREEN))
                        ).append(
                                Text.literal(" donated ")
                        ).append(
                                Text.literal(event.getBits() + " bits!!!").setStyle(Style.EMPTY.withColor(Formatting.LIGHT_PURPLE))
                        ));
                    }

                    CommandManager manager = server.getCommandManager();
                    manager.execute(manager.getDispatcher().parse(command, server.getCommandSource()), command);
                }
                break;
            default:
                break;
        }
    }
}
