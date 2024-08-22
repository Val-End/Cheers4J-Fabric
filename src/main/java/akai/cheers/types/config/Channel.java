package akai.cheers.types.config;

public class Channel {
    private String user;
    private String accessToken;
    private String refreshToken;

    public String getUser() { return user; }
    public void setUser(String value) { this.user = value; }

    public String getAccessToken() { return accessToken; }
    public void setAccessToken(String value) { this.accessToken = value; }

    public String getRefreshToken() { return refreshToken; }
    public void setRefreshToken(String value) { this.refreshToken = value; }
}
