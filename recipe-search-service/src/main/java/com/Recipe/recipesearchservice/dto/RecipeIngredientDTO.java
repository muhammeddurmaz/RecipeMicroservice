package com.Recipe.recipesearchservice.dto;

import java.io.Serializable;

public class RecipeIngredientDTO implements Serializable {
    private String amount;
    private IngredientDTO ingredient;

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public IngredientDTO getIngredient() {
        return ingredient;
    }

    public void setIngredient(IngredientDTO ingredient) {
        this.ingredient = ingredient;
    }
}
