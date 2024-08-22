package akai.cheers.types.config;

import com.google.gson.JsonObject;
import java.util.List;

public class Config {
    private List<Channel> channels;
    private JsonObject events;

    public List<Channel> getChannels() { return channels; }
    public void setChannels(List<Channel> value) { this.channels = value; }

    public JsonObject getEvents() { return events; }
    public void setEvents(JsonObject value) { this.events = value; }
}
