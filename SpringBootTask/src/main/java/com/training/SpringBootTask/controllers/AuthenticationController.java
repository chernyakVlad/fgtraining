package com.training.SpringBootTask.controllers;

import com.training.SpringBootTask.exceptions.UserValidationException;
import com.training.SpringBootTask.models.User;
import com.training.SpringBootTask.models.authentication.JwtToken;
import com.training.SpringBootTask.models.authentication.LoginUser;
import com.training.SpringBootTask.models.authentication.RegistrationUser;
import com.training.SpringBootTask.services.AuthenticationSerivce;
import com.training.SpringBootTask.services.TokenStore;
import com.training.SpringBootTask.services.UserService;
import com.training.SpringBootTask.services.impl.AuthenticationServiceImpl;
import com.training.SpringBootTask.services.impl.TokenStoreImpl;
import com.training.SpringBootTask.services.impl.UserServiceImpl;
import com.training.SpringBootTask.validators.RegistrationUserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1")
public class AuthenticationController {
    private UserService userService;
    private AuthenticationSerivce authenticationSerivce;
    private RegistrationUserValidator userValidator;
    private TokenStore tokenStore;

    @Autowired
    public AuthenticationController(UserServiceImpl userService,
                                    AuthenticationServiceImpl authenticationSerivce,
                                    RegistrationUserValidator userValidator,
                                    TokenStoreImpl tokenStore) {
        this.userService = userService;
        this.userValidator = userValidator;
        this.authenticationSerivce = authenticationSerivce;
        this.tokenStore = tokenStore;
    }

    @PostMapping(value="/registration")
    public ResponseEntity<?> registration(@RequestBody RegistrationUser rUser, BindingResult bindingResult) {
        userValidator.validate(rUser, bindingResult);
        if(bindingResult.hasErrors()){
            throw new UserValidationException(createExceptionMessage(bindingResult.getAllErrors()));
        }
        User user = new User(rUser.getLogin(), rUser.getPassword());
        Optional<User> userOptional = userService.save(user);
        return ResponseEntity.ok(userOptional.get());
    }

    @PostMapping(value = "/login")
    public ResponseEntity<JwtToken> login(@RequestBody LoginUser lUser) {
        JwtToken token = authenticationSerivce.login(lUser);
        return ResponseEntity.ok(token);
    }

    @PostMapping(value = "/refresh-token")
    public ResponseEntity<JwtToken> refresh(@RequestBody String refreshToken) {
        JwtToken token = authenticationSerivce.refresh(refreshToken);
        return ResponseEntity.ok(token);
    }

    @PostMapping(value = "/logout")
    public void logout(@RequestBody JwtToken accessToken) {
        tokenStore.removeToken(accessToken.getAccessToken());
    }

    private String createExceptionMessage(List<ObjectError> errors){
        StringBuilder builder = new StringBuilder();
        errors.forEach((error)->{
            builder.append(error.getDefaultMessage()).append("\n");
        });

        return builder.toString();
    }
}
