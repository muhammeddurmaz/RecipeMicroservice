package com.recipe.ingredientservice.dto.mapper;

import com.recipe.ingredientservice.dto.RecipeIngredientDTO;
import com.recipe.ingredientservice.model.RecipeIngredient;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RecipeIngredientMapper extends EntityMapper<RecipeIngredientDTO, RecipeIngredient>{
}
