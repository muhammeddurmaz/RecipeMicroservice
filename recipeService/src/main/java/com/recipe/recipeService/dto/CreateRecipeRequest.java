package com.recipe.recipeService.dto;

import java.util.HashMap;

public class CreateRecipeRequest {

    private String name;

    HashMap<Long,String> ingredient;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public HashMap<Long, String> getIngredient() {
        return ingredient;
    }

    public void setIngredient(HashMap<Long, String> ingredient) {
        this.ingredient = ingredient;
    }
}
