package akai.cheers.twitch.requests;

import akai.cheers.Cheers4J;
import akai.cheers.ConfigUtil;
import akai.cheers.types.config.Channel;
import akai.cheers.types.message.Condition;
import akai.cheers.types.message.Transport;
import akai.cheers.types.SubscriptionData;
import akai.cheers.types.user.User;
import com.google.gson.Gson;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpRequest.BodyPublishers;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;

public class RequestHandler {
    private static final Gson gson = new Gson();
    private static final HttpClient client = HttpClient.newHttpClient();

    public static void subscribe(String user, String access_token, String session_id) {
        try {
            SubscriptionData data = new SubscriptionData();
            data.setType("channel.cheer");
            data.setVersion("1");

            Condition condition = new Condition();
            condition.setBroadcasterUserID(getUser(user, access_token).getData()[0].getID());
            data.setCondition(condition);

            Transport transport = new Transport();
            transport.setMethod("websocket");
            transport.setSessionID(session_id);
            data.setTransport(transport);

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("https://api.twitch.tv/helix/eventsub/subscriptions"))
                    .header("Authorization", "Bearer " + access_token)
                    .header("Client-Id", env.getClientID())
                    .header("Content-Type", "application/json")
                    .POST(BodyPublishers.ofString(gson.toJson(data)))
                    .build();

            Cheers4J.LOGGER.info("{}({}) Subscribing to TwitchWSS", user, session_id);
            client.send(request, BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public static void refreshToken(String user, String refresh_token) {
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("https://api.twitch.tv/helix/users?login=" + user))
                    .header("Content-Type", "application/x-www-form-urlencoded")
                    .POST(BodyPublishers.ofString("client_id=" + env.getClientID()
                            + "&client_secret=" + env.getSecretID()
                            + "&grant_type=refresh_token"
                            + "&refresh_token=" + refresh_token))
                    .build();

            Cheers4J.LOGGER.info("{} Refreshing Token", user);

            HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
            Channel channel = gson.fromJson(response.body(), Channel.class);
            ConfigUtil.refreshUser(channel);
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public static User getUser(String user, String access_token) {
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("https://api.twitch.tv/helix/users?login=" + user))
                    .header("Authorization", "Bearer " + access_token)
                    .header("Client-Id", env.getClientID())
                    .build();

            Cheers4J.LOGGER.info("{} Getting Twitch User", user);
            HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
            return gson.fromJson(response.body(), User.class);
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
