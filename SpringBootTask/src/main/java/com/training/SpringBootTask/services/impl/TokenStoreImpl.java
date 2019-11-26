package com.training.SpringBootTask.services.impl;

import com.training.SpringBootTask.models.authentication.JwtToken;
import com.training.SpringBootTask.repositorys.TokenRepository;
import com.training.SpringBootTask.services.TokenStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.common.exceptions.InvalidTokenException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TokenStoreImpl implements TokenStore {

    private TokenRepository tokenRepository;

    @Autowired
    public TokenStoreImpl(TokenRepository tokenRepository)  {
        this.tokenRepository = tokenRepository;
    }

    @Override
    public void storeToken(JwtToken token) {
        tokenRepository.save(token);
    }

    @Override
    public void removeToken(String accessToken) {
        tokenRepository.deleteById(accessToken);
    }

    @Override
    public JwtToken checkToken(String accessToken) {
        Optional<JwtToken> jwtTokenOptional = tokenRepository.findByAccessToken(accessToken);
        jwtTokenOptional.orElseThrow(()-> new InvalidTokenException("Token has been invalid"));
        return jwtTokenOptional.get();
    }
}
