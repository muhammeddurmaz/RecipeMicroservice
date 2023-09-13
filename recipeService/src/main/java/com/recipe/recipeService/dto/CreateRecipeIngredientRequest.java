package com.recipe.recipeService.dto;

import java.util.HashMap;

public class CreateRecipeIngredientRequest {

    private Long recipeId;
    private HashMap<Long ,String> ingredients;


    public Long getRecipeId() {
        return recipeId;
    }

    public void setRecipeId(Long recipeId) {
        this.recipeId = recipeId;
    }

    public HashMap<Long, String> getIngredients() {
        return ingredients;
    }

    public void setIngredients(HashMap<Long, String> ingredients) {
        this.ingredients = ingredients;
    }
}
