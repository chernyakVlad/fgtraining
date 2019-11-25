package com.training.SpringBootTask.validators;

import com.training.SpringBootTask.models.User;
import com.training.SpringBootTask.models.authentication.RegistrationUser;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class RegistrationUserValidator implements Validator {
    @Override
    public boolean supports(Class<?> aClass) {
        return RegistrationUser.class.equals(aClass);
    }

    public void validate(Object o, Errors errors) {
        RegistrationUser user = (RegistrationUser) o;

        if(user.getLogin() == null) {
            errors.rejectValue("login", "negativeValue", new Object[]{"'login'"}, "login required");
        }

        if(user.getPassword().length() < 6){
            errors.rejectValue("password", "negativeValue", new Object[]{"'password'"}, "password length should be > 6");
        }
    }
}
