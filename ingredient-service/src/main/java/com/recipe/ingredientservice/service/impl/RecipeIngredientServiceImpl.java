package com.recipe.ingredientservice.service.impl;

import com.recipe.ingredientservice.dto.CreateRecipeIngredientRequest;
import com.recipe.ingredientservice.dto.IngredientDTO;
import com.recipe.ingredientservice.dto.RecipeIngredientDTO;
import com.recipe.ingredientservice.dto.ViewRecipeIngredientRequest;
import com.recipe.ingredientservice.dto.mapper.RecipeIngredientMapper;
import com.recipe.ingredientservice.repository.RecipeIngredientRepository;
import com.recipe.ingredientservice.service.IngredientService;
import com.recipe.ingredientservice.service.RecipeIngredientService;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
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
        List<IngredientDTO> ingredientDTOList = ingredientService.findAllIds(request.getIngredients());
        ViewRecipeIngredientRequest viewRecipeIngredientRequest = new ViewRecipeIngredientRequest();

        for(IngredientDTO i : ingredientDTOList){
            RecipeIngredientDTO recipeIngredientDTO = new RecipeIngredientDTO();
            recipeIngredientDTO.setIngredient(i);
            recipeIngredientDTO.setRecipeId(request.getRecipeId());
            recipeIngredientRepository.save(recipeIngredientMapper.toEntity(recipeIngredientDTO));
            viewRecipeIngredientRequest.getIngredients().add(i.getName());
        }

        viewRecipeIngredientRequest.setRecipeId(request.getRecipeId());
        return viewRecipeIngredientRequest;
    }

    @Override
    public ViewRecipeIngredientRequest getRecipeIngredients(Long recipeId) {
        return null;
    }
}
