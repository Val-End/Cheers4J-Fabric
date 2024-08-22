package akai.cheers.types.message;

public class Event {
    private int bits;
    private String broadcaster_user_id;
    private String broadcaster_user_login;
    private String broadcaster_user_name;
    private boolean is_anonymous;
    private String message;
    private String user_id;
    private String user_login;
    private String user_name;

    public int getBits() { return bits; }
    public void setBits(int value) { this.bits = value; }

    public String getBroadcasterUserID() { return broadcaster_user_id; }
    public void setBroadcasterUserID(String value) { this.broadcaster_user_id = value; }

    public String getBroadcasterUserLogin() { return broadcaster_user_login; }
    public void setBroadcasterUserLogin(String value) { this.broadcaster_user_login = value; }

    public String getBroadcasterUserName() { return broadcaster_user_name; }
    public void setBroadcasterUserName(String value) { this.broadcaster_user_name = value; }

    public boolean getIsAnonymous() { return is_anonymous; }
    public void setIsAnonymous(boolean value) { this.is_anonymous = value; }

    public String getMessage() { return message; }
    public void setMessage(String value) { this.message = value; }

    public String getUserID() { return user_id; }
    public void setUserID(String value) { this.user_id = value; }

    public String getUserLogin() { return user_login; }
    public void setUserLogin(String value) { this.user_login = value; }

    public String getUserName() { return user_name; }
    public void setUserName(String value) { this.user_name = value; }
}
