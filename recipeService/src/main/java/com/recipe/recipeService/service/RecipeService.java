package com.recipe.recipeService.service;

import com.recipe.recipeService.dto.RecipeDTO;


public interface RecipeService {

    RecipeDTO save(RecipeDTO recipeDTO);

    RecipeDTO findOneById(Long id);
}
