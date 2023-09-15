package com.recipe.recipeService.service.impl;

import com.recipe.recipeService.client.IngredientServiceClient;
import com.recipe.recipeService.dto.CreateRecipeIngredientRequest;
import com.recipe.recipeService.dto.CreateRecipeRequest;
import com.recipe.recipeService.dto.RecipeDTO;
import com.recipe.recipeService.dto.ViewRecipeIngredientRequest;
import com.recipe.recipeService.dto.mapper.RecipeMapper;
import com.recipe.recipeService.errors.BadRequestAlertException;
import com.recipe.recipeService.model.Recipe;
import com.recipe.recipeService.repository.RecipeRepository;
import com.recipe.recipeService.service.RecipeService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.Objects;
import java.util.stream.Collectors;

@Service
@Transactional
public class RecipeServiceImpl implements RecipeService {

    private final RecipeRepository recipeRepository;
    private final RecipeMapper recipeMapper;
    private final IngredientServiceClient ingredientServiceClient;

    public RecipeServiceImpl(RecipeRepository recipeRepository, RecipeMapper recipeMapper, IngredientServiceClient ingredientServiceClient) {
        this.recipeRepository = recipeRepository;
        this.recipeMapper = recipeMapper;
        this.ingredientServiceClient = ingredientServiceClient;
    }

    @Override
    public ViewRecipeIngredientRequest save(CreateRecipeRequest createRecipeRequest) {
        Recipe recipe = new Recipe();
        recipe.setName(createRecipeRequest.getName());
        recipe = recipeRepository.save(recipe);

        CreateRecipeIngredientRequest ingredientRequest = new CreateRecipeIngredientRequest();
        ingredientRequest.setRecipeId(recipe.getId());
        ingredientRequest.setIngredients(createRecipeRequest.getIngredient());

        return Objects.requireNonNull(ingredientServiceClient.saveRecipeIngredient(ingredientRequest).getBody()).getData();
    }

    @Override
    @Transactional(readOnly = true)
    public RecipeDTO findOneById(Long id) {
        return recipeRepository.findById(id)
                .map(recipeMapper::toDto)
                .orElseThrow(() -> new BadRequestAlertException("Recipe could not found by id " + id));
    }
}
