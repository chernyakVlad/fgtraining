package com.training.SpringBootTask.models.authentication;

public class JwtToken {
    private  String accessToken;
    private  String refreshToken;

    public JwtToken(String token, String refresh) {
        this.accessToken = token;
        this.refreshToken = refresh;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }
}
