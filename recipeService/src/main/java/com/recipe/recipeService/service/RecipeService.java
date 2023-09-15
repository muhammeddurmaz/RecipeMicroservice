package com.recipe.recipeService.service;

import com.recipe.recipeService.dto.CreateRecipeRequest;
import com.recipe.recipeService.dto.RecipeDTO;
import com.recipe.recipeService.dto.ViewRecipeIngredientRequest;


public interface RecipeService {

    ViewRecipeIngredientRequest save(CreateRecipeRequest createRecipeRequest);

    RecipeDTO findOneById(Long id);
}
