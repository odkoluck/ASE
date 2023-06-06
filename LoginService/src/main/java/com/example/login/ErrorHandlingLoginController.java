package com.example.login;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@ControllerAdvice
@RequestMapping
public class ErrorHandlingLoginController {
    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<ErrorObjectLogin> handleManageEventException(final AuthenticationException authenticationException) {
        ErrorObjectLogin toReturn = new ErrorObjectLogin(authenticationException.getMessage());
        return new ResponseEntity<>(toReturn, BAD_REQUEST);
    }
}