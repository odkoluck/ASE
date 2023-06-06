package com.example.Event;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@ControllerAdvice
@RequestMapping
public class ErrorHandlingController {
    @ExceptionHandler(ManageEventException.class)
    public ResponseEntity<ErrorObject> handleManageEventException(final ManageEventException manageEventException){
        ErrorObject toReturn = new ErrorObject(manageEventException.getMessage());
        return new ResponseEntity<>(toReturn, BAD_REQUEST);
    }
}
