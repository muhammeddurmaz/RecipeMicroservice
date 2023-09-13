package com.recipe.recipeService.dto;

import java.util.HashMap;

public class ViewRecipeIngredientRequest {

    private Long recipeId;
    private HashMap<String,String> ingredients;

    public Long getRecipeId() {
        return recipeId;
    }

    public void setRecipeId(Long recipeId) {
        this.recipeId = recipeId;
    }

    public HashMap<String, String> getIngredients() {
        return ingredients;
    }

    public void setIngredients(HashMap<String, String> ingredients) {
        this.ingredients = ingredients;
    }
}
