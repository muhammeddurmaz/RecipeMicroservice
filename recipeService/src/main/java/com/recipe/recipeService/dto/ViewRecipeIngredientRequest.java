package com.recipe.recipeService.dto;

import java.util.HashMap;
import java.util.List;

public class ViewRecipeIngredientRequest {

    private Long recipeId;
    private List<RecipeIngredientAmount> ingredients;

    public Long getRecipeId() {
        return recipeId;
    }

    public void setRecipeId(Long recipeId) {
        this.recipeId = recipeId;
    }

    public List<RecipeIngredientAmount> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<RecipeIngredientAmount> ingredients) {
        this.ingredients = ingredients;
    }
}
