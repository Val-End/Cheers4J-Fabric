package akai.cheers.types.message;

public class Metadata {
    private String message_id;
    private String message_type;
    private String message_timestamp;
    private String subscription_type;
    private String subscription_version;

    public String getMessageID() { return message_id; }
    public void setMessageID(String value) { this.message_id = value; }

    public String getMessageType() { return message_type; }
    public void setMessageType(String value) { this.message_type = value; }

    public String getMessageTimestamp() { return message_timestamp; }
    public void setMessageTimestamp(String value) { this.message_timestamp = value; }

    public String getSubscriptionType() { return subscription_type; }
    public void setSubscriptionType(String value) { this.subscription_type = value; }

    public String getSubscriptionVersion() { return subscription_version; }
    public void setSubscriptionVersion(String value) { this.subscription_version = value; }
}
