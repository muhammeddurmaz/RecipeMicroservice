package com.recipe.ingredientservice.dto;

import java.util.HashMap;
import java.util.List;

public class CreateRecipeIngredientRequest {

    private Long recipeId;
    private List<RecipeIngredientAmountDTO> ingredients;


    public Long getRecipeId() {
        return recipeId;
    }

    public void setRecipeId(Long recipeId) {
        this.recipeId = recipeId;
    }

    public List<RecipeIngredientAmountDTO> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<RecipeIngredientAmountDTO> ingredients) {
        this.ingredients = ingredients;
    }
}
