package com.training.SpringBootTask.services;

import com.training.SpringBootTask.models.authentication.JwtToken;
import com.training.SpringBootTask.models.authentication.LoginUser;
import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.security.core.AuthenticationException;

public interface AuthenticationSerivce {
    public JwtToken login(LoginUser loginUserForm) throws AuthenticationException, ExpiredJwtException;
    public JwtToken refresh(String refreshToken) throws AuthenticationException, ExpiredJwtException;
    public void logout();
}
