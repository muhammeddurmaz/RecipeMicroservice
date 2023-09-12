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
import org.springframework.transaction.annotation.Transactional;

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
        viewRecipeIngredientRequest.setIngredients(new HashMap<String ,String>());
        HashMap<Long,String> ingredients = request.getIngredients();

        for(Long id : ingredients.keySet()){
            IngredientDTO ingredientDTO = ingredientService.findOneById(id);

            RecipeIngredientDTO recipeIngredientDTO = new RecipeIngredientDTO();
            recipeIngredientDTO.setRecipeId(request.getRecipeId());
            recipeIngredientDTO.setIngredient(ingredientDTO);
            String amount = ingredients.get(id);
            recipeIngredientDTO.setAmount(amount);

            recipeIngredientRepository.save(recipeIngredientMapper.toEntity(recipeIngredientDTO));
            viewRecipeIngredientRequest.getIngredients().put(ingredientDTO.getName(),amount);
        }

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
        viewRecipeIngredientRequest.setIngredients(new HashMap<String,String>());
        recipeIngredients.forEach(dto -> viewRecipeIngredientRequest.getIngredients().put(dto.getIngredient().getName(),dto.getAmount()));
        viewRecipeIngredientRequest.setRecipeId(recipeId);

        return viewRecipeIngredientRequest;
    }
}
