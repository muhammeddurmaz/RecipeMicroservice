package com.recipe.recipeService.dto.mapper;

import com.recipe.recipeService.dto.RecipeDTO;
import com.recipe.recipeService.model.Recipe;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RecipeMapper extends EntityMapper<RecipeDTO, Recipe> {
}
