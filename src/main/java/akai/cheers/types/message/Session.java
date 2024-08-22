package akai.cheers.types.message;

public class Session {
    private String id;
    private String status;
    private long keepalive_timeout_seconds;
    private String reconnect_url;
    private String connected_at;

    public String getID() { return id; }
    public void setID(String value) { this.id = value; }

    public String getStatus() { return status; }
    public void setStatus(String value) { this.status = value; }

    public long getKeepaliveTimeoutSeconds() { return keepalive_timeout_seconds; }
    public void setKeepaliveTimeoutSeconds(long value) { this.keepalive_timeout_seconds = value; }

    public String getReconnectURL() { return reconnect_url; }
    public void setReconnectURL(String value) { this.reconnect_url = value; }

    public String getConnectedAt() { return connected_at; }
    public void setConnectedAt(String value) { this.connected_at = value; }
}
