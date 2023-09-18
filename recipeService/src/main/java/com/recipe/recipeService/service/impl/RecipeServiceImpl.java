package com.recipe.recipeService.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.recipe.recipeService.client.IngredientServiceClient;
import com.recipe.recipeService.dto.*;
import com.recipe.recipeService.dto.mapper.RecipeMapper;
import com.recipe.recipeService.errors.BadRequestAlertException;
import com.recipe.recipeService.model.Recipe;
import com.recipe.recipeService.repository.RecipeRepository;
import com.recipe.recipeService.service.RecipeService;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.Objects;

@Service
@Transactional
public class RecipeServiceImpl implements RecipeService {

    private final RecipeRepository recipeRepository;
    private final RecipeMapper recipeMapper;
    private final IngredientServiceClient ingredientServiceClient;
    private final KafkaTemplate<String , byte[]> kafkaTemplate;

    public RecipeServiceImpl(RecipeRepository recipeRepository, RecipeMapper recipeMapper, IngredientServiceClient ingredientServiceClient, KafkaTemplate<String , byte[]> kafkaTemplate) {
        this.recipeRepository = recipeRepository;
        this.recipeMapper = recipeMapper;
        this.ingredientServiceClient = ingredientServiceClient;
        this.kafkaTemplate = kafkaTemplate;
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

        ObjectMapper objectMapper = new ObjectMapper();
        byte[] serializedObject;
        try {
            serializedObject = objectMapper.writeValueAsBytes(viewRecipeDetail);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }


        kafkaTemplate.send("recipe-detail-elasticsearch",serializedObject);

//        ingredientRequest = null;
//        resultRecipeIngredient = null;
//        recipe = null;
        return viewRecipeDetail;
    }

    @Override
    @Transactional(readOnly = true)
    public RecipeDTO findOneById(Long id) {
        return recipeRepository.findById(id)
                .map(recipeMapper::toDto)
                .orElseThrow(() -> new BadRequestAlertException("Recipe could not found by id " + id));
    }
}
