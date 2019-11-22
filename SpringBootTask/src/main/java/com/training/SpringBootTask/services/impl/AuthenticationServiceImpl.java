package com.training.SpringBootTask.services.impl;

import com.training.SpringBootTask.models.authentication.JwtToken;
import com.training.SpringBootTask.models.authentication.LoginUserForm;
import com.training.SpringBootTask.security.JwtTokenProvider;
import com.training.SpringBootTask.services.AuthenticationSerivce;
import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationServiceImpl implements AuthenticationSerivce {

    private AuthenticationManager authenticationManager;
    private JwtTokenProvider tokenProvider;
    @Autowired
    @Qualifier("customUserDetailsService")
    private UserDetailsService userDetailsService;

    @Autowired
    public AuthenticationServiceImpl(AuthenticationManager authenticationManager, JwtTokenProvider jwtTokenProvider) {
        this.authenticationManager = authenticationManager;
        this.tokenProvider = jwtTokenProvider;
    }

    @Override
    public JwtToken login(LoginUserForm loginUserForm) throws AuthenticationException, ExpiredJwtException {
        final Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginUserForm.getLogin(),
                        loginUserForm.getPassword()
                )
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        final String token = tokenProvider.generateToken(authentication);
        final String refreshToken = tokenProvider.generateRefreshToken(authentication);
        return new JwtToken(token, refreshToken);
    }

    @Override
    public JwtToken refresh(String refreshToken) throws AuthenticationException, ExpiredJwtException {
        String username = tokenProvider.getUsernameFromToken(refreshToken);
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        if(tokenProvider.validateToken(refreshToken, userDetails)){
            final Authentication authentication = new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);
            final String token = tokenProvider.generateToken(authentication);
            final String newRefreshToken = tokenProvider.generateRefreshToken(authentication);
            return new JwtToken(token, newRefreshToken);
        }
        return null;
    }



    @Override
    public void logout() {

    }
}

