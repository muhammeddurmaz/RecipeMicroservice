package com.recipe.recipeService.dto;

import java.util.HashMap;
import java.util.List;

public class CreateRecipeRequest {

    private String name;

    private List<RecipeIngredientAmount> ingredients;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<RecipeIngredientAmount> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<RecipeIngredientAmount> ingredients) {
        this.ingredients = ingredients;
    }
}
