package com.training.SpringBootTask.services;

import com.training.SpringBootTask.models.authentication.JwtToken;
import com.training.SpringBootTask.models.authentication.LoginUserForm;
import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.security.core.AuthenticationException;

public interface AuthenticationSerivce {
    public JwtToken login(LoginUserForm loginUserForm) throws AuthenticationException, ExpiredJwtException;
    public void logout();
}
