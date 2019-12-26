package com.training.SpringBootTask.validators;

import com.training.SpringBootTask.entity.User;
import com.training.SpringBootTask.entity.authentication.RegistrationUser;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Service
public class UserValidator implements Validator {

    @Override
    public boolean supports(Class<?> aClass) {
        return RegistrationUser.class.equals(aClass);
    }

    public void validate(Object o, Errors errors) {
        User user = (User) o;
    }
}
