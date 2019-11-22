package com.training.SpringBootTask.controllers;

import com.training.SpringBootTask.models.User;
import com.training.SpringBootTask.models.authentication.JwtToken;
import com.training.SpringBootTask.models.authentication.LoginUserForm;
import com.training.SpringBootTask.repositorys.UserRepository;
import com.training.SpringBootTask.services.AuthenticationSerivce;
import com.training.SpringBootTask.services.UserService;
import com.training.SpringBootTask.services.impl.AuthenticationServiceImpl;
import com.training.SpringBootTask.services.impl.UserServiceImpl;
import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.core.AuthenticationException;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1")
public class AuthenticationController {
    private UserService userService;
    private AuthenticationSerivce authenticationSerivce;

    @Autowired
    public AuthenticationController(UserServiceImpl userService, AuthenticationServiceImpl authenticationSerivce) {
        this.userService = userService;
        this.authenticationSerivce = authenticationSerivce;
    }

    @PostMapping(value="/registration")
    public ResponseEntity<User> createUser(@RequestBody User pUser) {
        Optional<User> user = userService.save(pUser);
        return ResponseEntity.ok(user.get());
    }

    @PostMapping(value = "/login")
    public ResponseEntity<JwtToken> login(@RequestBody LoginUserForm loginUserForm) {
        try {
            JwtToken token = authenticationSerivce.login(loginUserForm);
            return ResponseEntity.ok(token);
        } catch (AuthenticationException ex) {
            return new ResponseEntity("Authentication Failed", HttpStatus.BAD_REQUEST);
        } catch (ExpiredJwtException ex) {
            return new ResponseEntity("Token Expired", HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value = "/logout")
    public void logout() {
    }


}
