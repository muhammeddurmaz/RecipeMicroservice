package com.recipe.recipeService.errors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GeneralExceptionHandler {

    @ExceptionHandler(BadRequestAlertException.class)
    public ResponseEntity<?> handleBadRequestAlertException(BadRequestAlertException exception){
        return new ResponseEntity<>(exception.getMessage() , HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(BadRequestAlertException.class)
    public ResponseEntity<?> handleException(BadRequestAlertException exception){
        return new ResponseEntity<>(exception.getExceptionMessage() , HttpStatus.NOT_FOUND);
    }
}
