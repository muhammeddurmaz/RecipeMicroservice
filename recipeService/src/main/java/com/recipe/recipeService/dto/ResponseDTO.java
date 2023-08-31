package com.recipe.recipeService.dto;

import java.io.Serializable;
import java.util.HashMap;

public class ResponseDTO<T> implements Serializable {

    private HashMap<String,String> message;
    private boolean success;
    private T data;

    public HashMap<String, String> getMessage() {
        return message;
    }

    public void setMessage(String message,String entityName) {
        HashMap<String,String> map = new HashMap<>();
        map.put("entity",entityName);
        map.put("message",message);
        this.message = map;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
