package com.training.SpringBootTask.services;

import com.training.SpringBootTask.entity.authentication.JwtToken;

public interface TokenStore {
    public void storeToken(JwtToken token);
    public void removeToken(String accessToken);
    public JwtToken checkToken(String accessToken);
}
