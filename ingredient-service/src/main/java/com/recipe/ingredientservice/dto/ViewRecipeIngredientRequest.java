package com.recipe.ingredientservice.dto;

import java.util.List;

public class ViewRecipeIngredientRequest {

    private Long recipeId;
    private List<String> ingredients;

    public Long getRecipeId() {
        return recipeId;
    }

    public void setRecipeId(Long recipeId) {
        this.recipeId = recipeId;
    }

    public List<String> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<String> ingrediens) {
        this.ingredients = ingrediens;
    }
}
