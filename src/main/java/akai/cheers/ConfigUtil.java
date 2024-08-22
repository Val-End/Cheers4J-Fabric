package akai.cheers;

import akai.cheers.types.config.Channel;
import akai.cheers.types.config.Config;
import com.google.common.collect.ImmutableMap;
import com.google.gson.*;
import net.fabricmc.loader.api.FabricLoader;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.BiConsumer;

public class ConfigUtil {
    private static final Gson GSON = new Gson();
    private static final Path configPath = FabricLoader.getInstance().getConfigDir().resolve("twitch.json");
    private static Config config;
    private static Map<String, String> events;

    public static boolean load() {
        Cheers4J.LOGGER.info("Loading Config");

        config = getDefaultConfig();
        ImmutableMap.Builder<String, String> builder = ImmutableMap.builder();
        BiConsumer<String, String> biConsumer = builder::put;

        try {
            if (Files.exists(configPath)) {
                BufferedReader reader = Files.newBufferedReader(configPath);
                config = GSON.fromJson(reader, Config.class);
                reader.close();

                for(Map.Entry<String, JsonElement> entry : config.getEvents().entrySet()){
                    biConsumer.accept(entry.getKey(), entry.getValue().getAsString());
                }

                events = builder.build();
                Cheers4J.LOGGER.info("Config Loaded Successfully");
            }
            else
                return create();

        } catch (IOException e) {
            Cheers4J.LOGGER.error("Error loading config file.", e);
        }

        return false;
    }

    private static Config getDefaultConfig() {
        Config defaultConfig = new Config();

        ArrayList<Channel> channels = new ArrayList<>();
        Channel channel = new Channel();
        channel.setUser("ExampleUser");
        channel.setAccessToken("put-your-access-token-here");
        channel.setRefreshToken("put-your-refresh-token-here");
        channels.add(channel);
        defaultConfig.setChannels(channels);

        JsonObject events = new JsonObject();
        events.addProperty("100", "put-a-command-here");
        defaultConfig.setEvents(events);
        return defaultConfig;
    }

    private static boolean create() throws IOException  {
        Cheers4J.LOGGER.warn("Config file not found, creating one");
        Files.createFile(configPath);
        save();
        return true;
    }

    private static void save() throws IOException{
        Gson prettyGson = new Gson().newBuilder().setPrettyPrinting().create();
        BufferedWriter writer = Files.newBufferedWriter(configPath);
        writer.write(prettyGson.toJson(config));
        writer.close();
    }

    public static List<Channel> getChannels() {
        return config.getChannels();
    }

    public static String getEvent(int bits) {
        if(events.containsKey(Integer.toString(bits)))
            return events.get(Integer.toString(bits));

        for(Map.Entry<String, String> entry : events.entrySet()){
            if(!entry.getKey().contains(","))
                continue;

            String[] values = entry.getKey().replaceAll("\\s+","").split(",");
            if(bits >= Integer.parseInt(values[0]) && bits <= Integer.parseInt(values[1]))
                return entry.getValue();
        }

        return "";
    }

    public static void refreshUser(Channel newChannel) {
        for(Channel channel : config.getChannels()) {
            if(Objects.equals(channel.getUser(), newChannel.getUser()))
                channel.setAccessToken(newChannel.getAccessToken());
        }

        try {
            save();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

