package com.recipe.ingredientservice.exception;

public class BadRequestAlertException extends RuntimeException{
    public BadRequestAlertException(String message){
        super(message);
    }
}
