package com.recipe.ingredientservice.service.impl;

import com.recipe.ingredientservice.dto.*;
import com.recipe.ingredientservice.dto.mapper.RecipeIngredientMapper;
import com.recipe.ingredientservice.repository.RecipeIngredientRepository;
import com.recipe.ingredientservice.service.IngredientService;
import com.recipe.ingredientservice.service.RecipeIngredientService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class RecipeIngredientServiceImpl implements RecipeIngredientService {

    private final IngredientService ingredientService;
    private final RecipeIngredientRepository recipeIngredientRepository;
    private final RecipeIngredientMapper recipeIngredientMapper;
    public RecipeIngredientServiceImpl(IngredientService ingredientService, RecipeIngredientRepository recipeIngredientRepository, RecipeIngredientMapper recipeIngredientMapper) {
        this.ingredientService = ingredientService;
        this.recipeIngredientRepository = recipeIngredientRepository;
        this.recipeIngredientMapper = recipeIngredientMapper;
    }

    @Override
    public ViewRecipeIngredientRequest saveRecipeIngredient(CreateRecipeIngredientRequest request) {
        ViewRecipeIngredientRequest viewRecipeIngredientRequest = new ViewRecipeIngredientRequest();


        for(RecipeIngredientAmountDTO recipeAmount : request.getIngredients()){

            RecipeIngredientDTO recipeIngredientDTO = new RecipeIngredientDTO();
            recipeIngredientDTO.setRecipeId(request.getRecipeId());
            recipeIngredientDTO.setIngredient(recipeAmount.getIngredient());
            String amount = recipeAmount.getAmount();
            recipeIngredientDTO.setAmount(amount);

            recipeIngredientRepository.save(recipeIngredientMapper.toEntity(recipeIngredientDTO));

        }
        viewRecipeIngredientRequest.setIngredients(request.getIngredients());
        viewRecipeIngredientRequest.setRecipeId(request.getRecipeId());
        return viewRecipeIngredientRequest;
    }

    @Override
    @Transactional(readOnly = true)
    public ViewRecipeIngredientRequest getRecipeIngredients(Long recipeId) {

        List<RecipeIngredientDTO> recipeIngredients =  recipeIngredientRepository.findByRecipeId(recipeId)
                .stream()
                .map(recipeIngredientMapper::toDto).collect(Collectors.toList());

        ViewRecipeIngredientRequest viewRecipeIngredientRequest = new ViewRecipeIngredientRequest();
        List<RecipeIngredientAmountDTO> recipeAmountList = new ArrayList<>();

        recipeIngredients.forEach(dto -> {
            RecipeIngredientAmountDTO recipeAmountDTO = new RecipeIngredientAmountDTO();
            recipeAmountDTO.setAmount(dto.getAmount());
            recipeAmountDTO.setIngredient(dto.getIngredient());
            recipeAmountList.add(recipeAmountDTO);
        });

        viewRecipeIngredientRequest.setRecipeId(recipeId);
        viewRecipeIngredientRequest.setIngredients(recipeAmountList);

        return viewRecipeIngredientRequest;
    }
}
