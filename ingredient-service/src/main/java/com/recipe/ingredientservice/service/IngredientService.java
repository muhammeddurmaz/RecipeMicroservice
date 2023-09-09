package com.recipe.ingredientservice.service;

import com.recipe.ingredientservice.dto.CreateIngredientRequest;
import com.recipe.ingredientservice.dto.IngredientDTO;

import java.util.List;
import java.util.Set;

public interface IngredientService {
    IngredientDTO save(CreateIngredientRequest ingredientDTO);

    IngredientDTO findOneById(Long id);

    List<IngredientDTO> findAllIds(Set<Long> ids);
}
