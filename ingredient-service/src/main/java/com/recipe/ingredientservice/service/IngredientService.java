package com.recipe.ingredientservice.service;

import com.recipe.ingredientservice.dto.CreateIngredientRequest;
import com.recipe.ingredientservice.dto.IngredientDTO;

import java.util.List;

public interface IngredientService {
    IngredientDTO save(CreateIngredientRequest ingredientDTO);

    IngredientDTO findOneById(Long id);

    List<IngredientDTO> findAllIds(List<Long> ids);
}
