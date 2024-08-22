package akai.cheers.types.user;

public class Datum {
    private String id;
    private String login;
    private String display_name;
    private String type;
    private String broadcaster_type;
    private String description;
    private String profile_image_url;
    private String offline_image_url;
    private int view_count;
    private String created_at;

    public String getID() { return id; }
    public void setID(String value) { this.id = value; }

    public String getLogin() { return login; }
    public void setLogin(String value) { this.login = value; }

    public String getDisplayName() { return display_name; }
    public void setDisplayName(String value) { this.display_name = value; }

    public String getType() { return type; }
    public void setType(String value) { this.type = value; }

    public String getBroadcasterType() { return broadcaster_type; }
    public void setBroadcasterType(String value) { this.broadcaster_type = value; }

    public String getDescription() { return description; }
    public void setDescription(String value) { this.description = value; }

    public String getProfileImageURL() { return profile_image_url; }
    public void setProfileImageURL(String value) { this.profile_image_url = value; }

    public String getOfflineImageURL() { return offline_image_url; }
    public void setOfflineImageURL(String value) { this.offline_image_url = value; }

    public int getViewCount() { return view_count; }
    public void setViewCount(int value) { this.view_count = value; }

    public String getCreatedAt() { return created_at; }
    public void setCreatedAt(String value) { this.created_at = value; }
}
