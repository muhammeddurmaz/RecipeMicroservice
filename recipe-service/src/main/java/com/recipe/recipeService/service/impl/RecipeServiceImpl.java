package com.recipe.recipeService.service.impl;

import com.recipe.recipeService.client.IngredientServiceClient;
import com.recipe.recipeService.client.RecipeSearchServiceClient;
import com.recipe.recipeService.dto.*;
import com.recipe.recipeService.dto.mapper.RecipeMapper;
import com.recipe.recipeService.errors.BadRequestAlertException;
import com.recipe.recipeService.model.Recipe;
import com.recipe.recipeService.repository.RecipeRepository;
import com.recipe.recipeService.service.RecipeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

@Service
@Transactional
public class RecipeServiceImpl implements RecipeService {

    private final Logger logger = LoggerFactory.getLogger(RecipeServiceImpl.class);
    private final RecipeRepository recipeRepository;
    private final RecipeMapper recipeMapper;
    private final IngredientServiceClient ingredientServiceClient;
    private final RecipeSearchServiceClient recipeSearchServiceClient;

    public RecipeServiceImpl(RecipeRepository recipeRepository, RecipeMapper recipeMapper, IngredientServiceClient ingredientServiceClient, RecipeSearchServiceClient recipeSearchServiceClient) {
        this.recipeRepository = recipeRepository;
        this.recipeMapper = recipeMapper;
        this.ingredientServiceClient = ingredientServiceClient;
        this.recipeSearchServiceClient = recipeSearchServiceClient;
    }

    @Override
    public ViewRecipeDetail save(CreateRecipeRequest createRecipeRequest) {
        Recipe recipe = new Recipe();
        recipe.setName(createRecipeRequest.getName());
        recipe = recipeRepository.save(recipe);

        CreateRecipeIngredientRequest ingredientRequest = new CreateRecipeIngredientRequest();
        ingredientRequest.setRecipeId(recipe.getId());
        ingredientRequest.setIngredients(createRecipeRequest.getIngredients());

        ViewRecipeIngredientRequest resultRecipeIngredient = Objects.requireNonNull(ingredientServiceClient.saveRecipeIngredient(ingredientRequest).getBody()).getData();

        ViewRecipeDetail viewRecipeDetail = new ViewRecipeDetail();
        viewRecipeDetail.setId(recipe.getId());
        viewRecipeDetail.setName(recipe.getName());
        viewRecipeDetail.setIngredients(resultRecipeIngredient.getIngredients());

        Runnable run = () -> saveRecipeSearch(viewRecipeDetail);
        Thread thread = new Thread(run);
        thread.start();
//        recipeSearchServiceClient.saveRecipeSearch(viewRecipeDetail);

        return viewRecipeDetail;
    }


    @Override
    @Transactional(readOnly = true)
    public RecipeDTO findOneById(Long id) {
        return recipeRepository.findById(id)
                .map(recipeMapper::toDto)
                .orElseThrow(() -> new BadRequestAlertException("Recipe could not found by id " + id));
    }

    private void saveRecipeSearch(ViewRecipeDetail recipeDetail){
        ViewRecipeDetail entity =  recipeSearchServiceClient.saveRecipeSearch(recipeDetail).getBody();
        if (Objects.isNull(entity)){
            Recipe db = recipeRepository.findById(recipeDetail.getId()).orElseThrow( () -> new BadRequestAlertException("Not Found"));
            entity.setId(db.getId());
            entity.setName(db.getName());
            entity.setIngredients(recipeDetail.getIngredients());
            saveRecipeSearch(entity);
        }
    }
}