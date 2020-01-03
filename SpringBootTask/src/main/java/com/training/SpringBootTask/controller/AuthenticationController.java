package com.training.SpringBootTask.controller;

import com.training.SpringBootTask.exception.UserValidationException;
import com.training.SpringBootTask.entity.User;
import com.training.SpringBootTask.entity.authentication.JwtToken;
import com.training.SpringBootTask.entity.authentication.LoginUser;
import com.training.SpringBootTask.entity.authentication.RegistrationUser;
import com.training.SpringBootTask.services.AuthenticationSerivce;
import com.training.SpringBootTask.services.TokenStore;
import com.training.SpringBootTask.services.impl.AuthenticationServiceImpl;
import com.training.SpringBootTask.services.impl.TokenStoreImpl;
import com.training.SpringBootTask.validators.LoginUserValidator;
import com.training.SpringBootTask.validators.RegistrationUserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "http://localhost:3000")
public class AuthenticationController {
    private AuthenticationSerivce authSerivce;
    private RegistrationUserValidator regUserValidator;
    private LoginUserValidator loginUserValidator;
    private TokenStore tokenStore;

    @Autowired
    public AuthenticationController(AuthenticationServiceImpl authenticationSerivce,
                                    RegistrationUserValidator regUserValidator,
                                    LoginUserValidator loginUserValidator,
                                    TokenStoreImpl tokenStore) {
        this.regUserValidator = regUserValidator;
        this.loginUserValidator = loginUserValidator;
        this.authSerivce = authenticationSerivce;
        this.tokenStore = tokenStore;
    }

    @PostMapping(value="/registration")
    public ResponseEntity<User> signUp(@RequestBody RegistrationUser rUser, BindingResult bindingResult) {
        regUserValidator.validate(rUser, bindingResult);
        if(bindingResult.hasErrors()){
            throw new UserValidationException(RestExceptionHandler.createExceptionMessage(bindingResult.getAllErrors()));
        }
        return ResponseEntity.ok(authSerivce.registration(rUser));
    }

    @PostMapping(value = "/login")
    public ResponseEntity<JwtToken> signIn(@RequestBody LoginUser lUser, BindingResult bindingResult) {
        loginUserValidator.validate(lUser, bindingResult);
        if(bindingResult.hasErrors()) {
            throw new UserValidationException(RestExceptionHandler.createExceptionMessage(bindingResult.getAllErrors()));
        }
        return ResponseEntity.ok(authSerivce.login(lUser));
    }

    @PostMapping(value = "/resetPassword")
    public ResponseEntity<?> resetPassword(@RequestParam String password, @RequestParam String newPassword) {
        String login = SecurityContextHolder.getContext().getAuthentication().getName();
        authSerivce.resetPassword(login, password, newPassword);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping(value = "/refresh-token")
    public ResponseEntity<JwtToken> refresh(@RequestBody String refreshToken) {
        return ResponseEntity.ok(authSerivce.refresh(refreshToken));
    }

    @PostMapping(value = "/logout")
    public void logout(@RequestBody JwtToken accessToken) {
        tokenStore.removeToken(accessToken.getAccessToken());
    }
}
