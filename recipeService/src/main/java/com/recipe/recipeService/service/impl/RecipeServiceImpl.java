package com.recipe.recipeService.service.impl;

import com.recipe.recipeService.dto.RecipeDTO;
import com.recipe.recipeService.dto.mapper.RecipeMapper;
import com.recipe.recipeService.errors.BadRequestAlertException;
import com.recipe.recipeService.model.Recipe;
import com.recipe.recipeService.repository.RecipeRepository;
import com.recipe.recipeService.service.RecipeService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RecipeServiceImpl implements RecipeService {

    private final RecipeRepository recipeRepository;
    private final RecipeMapper recipeMapper;

    public RecipeServiceImpl(RecipeRepository recipeRepository, RecipeMapper recipeMapper) {
        this.recipeRepository = recipeRepository;
        this.recipeMapper = recipeMapper;
    }

    @Override
    public RecipeDTO save(RecipeDTO recipeDTO) {
        Recipe recipe = recipeMapper.toEntity(recipeDTO);
        recipe = recipeRepository.save(recipe);
        return recipeMapper.toDto(recipe);
    }

    @Override
    public RecipeDTO findOneById(Long id) {
        return recipeRepository.findById(id)
                .map(recipeMapper::toDto)
                .orElseThrow(() -> new BadRequestAlertException("Recipe could not found by id " + id));
    }
}
