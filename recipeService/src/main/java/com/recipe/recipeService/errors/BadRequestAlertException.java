package com.recipe.recipeService.errors;

public class BadRequestAlertException extends RuntimeException{
    private ExceptionMessage exceptionMessage;
    public BadRequestAlertException(String message){
        super(message);
    }

    public BadRequestAlertException(ExceptionMessage message){
        this.exceptionMessage = message;
    }

    public ExceptionMessage getExceptionMessage() {
        return exceptionMessage;
    }
}
