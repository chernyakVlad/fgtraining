package com.training.SpringBootTask.controllers;

import com.training.SpringBootTask.exceptions.ItemNotFoundException;
import com.training.SpringBootTask.exceptions.UserValidationException;
import io.jsonwebtoken.ExpiredJwtException;
//import org.springframework.hateoas.mediatype.vnderrors.VndErrors;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.nio.file.NoSuchFileException;
import java.util.Optional;

@ControllerAdvice
public class RestExceptionHandler {

    private final MediaType vndErrorMediaType = MediaType.parseMediaType("application/vnd.error");

    @ExceptionHandler(AuthenticationException.class)
    protected ResponseEntity<ExceptionEntity> handleAuthenticationException(AuthenticationException e) {
        return new ResponseEntity<>(new ExceptionEntity(e.getMessage()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ExpiredJwtException.class)
    protected ResponseEntity<ExceptionEntity> handleExpiredJwtException(ExpiredJwtException e) {
        return new ResponseEntity<>(new ExceptionEntity(e.getMessage()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(UserValidationException.class)
    protected ResponseEntity<ExceptionEntity> handleUserValidationException(final UserValidationException e) {
        return new ResponseEntity<>(new ExceptionEntity(e.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NoSuchFileException.class)
    protected ResponseEntity<ExceptionEntity> handleExpiredJwtException(NoSuchFileException e) {
        return new ResponseEntity<>(new ExceptionEntity("File is not found."), HttpStatus.NOT_FOUND);
    }

  /*  @ExceptionHandler(ItemNotFoundException.class)
    protected ResponseEntity<VndErrors> handleExpiredJwtException(ItemNotFoundException e) {
        return this.error(e, HttpStatus.NOT_FOUND, "1");
        //return new ResponseEntity<>(new ExceptionEntity(e.getMessage()), HttpStatus.NOT_FOUND);
    }*/

    static class ExceptionEntity {
        private String message;

        ExceptionEntity(String message) {
            this.message = message;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }
    }

 /*   private <E extends Exception> ResponseEntity<VndErrors> error(E error, HttpStatus httpStatus, String logref) {
        String msg = Optional.of(error.getMessage())
                .orElse(error.getClass().getSimpleName());

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(this.vndErrorMediaType);
        return new ResponseEntity<>(new VndErrors(logref, msg), httpHeaders, httpStatus);
    }*/
}
