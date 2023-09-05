package com.recipe.ingredientservice.service;

import com.recipe.ingredientservice.dto.CreateRecipeIngredientRequest;
import com.recipe.ingredientservice.dto.ViewRecipeIngredientRequest;

public interface RecipeIngredientService {

    ViewRecipeIngredientRequest saveRecipeIngredient(CreateRecipeIngredientRequest request);

    ViewRecipeIngredientRequest getRecipeIngredients(Long recipeId);
}
