package com.recipe.recipeService.service;

import com.recipe.recipeService.dto.CreateRecipeRequest;
import com.recipe.recipeService.dto.RecipeDTO;
import com.recipe.recipeService.dto.ViewRecipeDetail;
import com.recipe.recipeService.dto.ViewRecipeIngredientRequest;


public interface RecipeService {

    ViewRecipeDetail save(CreateRecipeRequest createRecipeRequest);

    RecipeDTO findOneById(Long id);
}
