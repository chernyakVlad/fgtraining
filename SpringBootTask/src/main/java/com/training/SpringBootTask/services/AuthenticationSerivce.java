package com.training.SpringBootTask.services;

import com.training.SpringBootTask.models.User;
import com.training.SpringBootTask.models.authentication.JwtToken;
import com.training.SpringBootTask.models.authentication.LoginUser;
import com.training.SpringBootTask.models.authentication.RegistrationUser;
import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.security.core.AuthenticationException;

public interface AuthenticationSerivce {
    public User registration(RegistrationUser registrationUser);
    public JwtToken login(LoginUser loginUserForm) throws AuthenticationException, ExpiredJwtException;
    public JwtToken refresh(String refreshToken) throws AuthenticationException, ExpiredJwtException;
    public void resetPassword(String login, String password, String newPassword);
    public void logout(String accessToken);
}
