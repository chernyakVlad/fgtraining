package com.training.SpringBootTask.validators;

import com.training.SpringBootTask.models.User;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class UserValidator implements Validator {
    @Override
    public boolean supports(Class<?> aClass) {
        return User.class.equals(aClass);
    }

    public void validate(Object o, Errors errors) {
        User user = (User) o;

        if(user.getLogin() == null) {
            errors.rejectValue("login", "negativeValue", new Object[]{"'login'"}, "login required");
        }

        if(user.getPassword().length() < 6){
            errors.rejectValue("password", "negativeValue", new Object[]{"'password'"}, "password length should be > 6");
        }
    }
}
