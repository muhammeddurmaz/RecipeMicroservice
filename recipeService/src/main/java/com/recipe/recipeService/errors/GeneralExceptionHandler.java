package com.recipe.recipeService.errors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.NativeWebRequest;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.Locale;

@ControllerAdvice
public class GeneralExceptionHandler {

//    @ExceptionHandler(BadRequestAlertException.class)
//    public ResponseEntity<?> handleBadRequestAlertException(BadRequestAlertException exception){
//        return new ResponseEntity<>(exception.getMessage() , HttpStatus.NOT_FOUND);
//    }

    @ExceptionHandler(BadRequestAlertException.class)
    public ResponseEntity<?> handleException(BadRequestAlertException exception, NativeWebRequest request){
        if(exception.getExceptionMessage() == null){
            ExceptionMessage exceptionMessage = new ExceptionMessage();
            exceptionMessage.setMessage(exception.getMessage());
            exceptionMessage.setPath(request.getContextPath());
            exceptionMessage.setTimestamp(LocalDateTime.now().toString());
            exceptionMessage.setStatus(404);
            exceptionMessage.setError("NOT_FOUND");
            return new ResponseEntity<>(exceptionMessage,HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(exception.getExceptionMessage(), HttpStatus.NOT_FOUND);
    }
}
