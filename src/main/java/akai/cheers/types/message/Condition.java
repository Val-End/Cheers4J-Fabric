package akai.cheers.types.message;

public class Condition {
    private String broadcaster_user_id;
    private String moderator_user_id;

    public String getBroadcasterUserID() { return broadcaster_user_id; }
    public void setBroadcasterUserID(String value) { this.broadcaster_user_id = value; }

    public String getModeratorUserID() { return moderator_user_id; }
    public void setModeratorUserID(String value) { this.moderator_user_id = value; }
}
