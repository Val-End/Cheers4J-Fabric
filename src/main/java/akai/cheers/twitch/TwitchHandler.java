package akai.cheers.twitch;

import akai.cheers.Cheers4J;
import akai.cheers.ConfigUtil;
import akai.cheers.types.config.Channel;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.WebSocket;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class TwitchHandler {
    private static final HttpClient client = HttpClient.newHttpClient();
    private static final String wss_url = "ws://127.0.0.1:8080/ws";
    private static final List<WebSocket> sockets = new CopyOnWriteArrayList<>();
    private static boolean connected = false;

    public static void connect(){
        if(connected)
            return; // Is already connected

        if(!ConfigUtil.load()) { // Check if config has been created
            Cheers4J.LOGGER.info("Connecting Sockets...");
            List<Channel> channels = ConfigUtil.getChannels();

            for(Channel channel : channels) {
                WebSocket socket = client.newWebSocketBuilder()
                        .buildAsync(URI.create(wss_url), new TwitchListener(channel))
                        .join();

                sockets.add(socket);
            }

            connected = true;
        }
    }

    public static void disconnect()  {
        if(sockets.isEmpty() || !connected)
            return; // Is already disconnected | Users not loaded.

        for(WebSocket socket : sockets){
            Cheers4J.LOGGER.info("Disconnecting Socket");
            socket.sendClose(WebSocket.NORMAL_CLOSURE, "Manual Closure");
            sockets.remove(socket);
            Cheers4J.LOGGER.info("Disconnected");
        }

        TwitchListener.stopped = true;
        connected = false;
    }
}
