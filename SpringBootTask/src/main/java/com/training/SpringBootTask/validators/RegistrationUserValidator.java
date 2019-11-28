package com.training.SpringBootTask.validators;

import com.training.SpringBootTask.models.authentication.RegistrationUser;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Service
public class RegistrationUserValidator implements Validator {
    private static final int MINIMUM_PASSWORD_LENGTH = 6;
    private static final int MINIMUM_LOGIN_LENGTH = 6;

    @Override
    public boolean supports(Class<?> aClass) {
        return RegistrationUser.class.equals(aClass);
    }

    public void validate(Object o, Errors errors) {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "login", "field required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "field required");

        RegistrationUser user = (RegistrationUser) o;

        if (user.getPassword() != null && user.getPassword().trim().length() < MINIMUM_PASSWORD_LENGTH) {
            errors.rejectValue("password", "field.min.length",
                    new Object[]{Integer.valueOf(MINIMUM_PASSWORD_LENGTH)},
                    "The password must be at least [" + MINIMUM_PASSWORD_LENGTH + "] characters in length.");
        }

        if (user.getLogin() != null && user.getLogin().trim().length() < MINIMUM_LOGIN_LENGTH) {
            errors.rejectValue("login", "field.min.length",
                    new Object[]{Integer.valueOf(MINIMUM_LOGIN_LENGTH)},
                    "The login must be at least [" + MINIMUM_LOGIN_LENGTH + "] characters in length.");
        }
    }
}
