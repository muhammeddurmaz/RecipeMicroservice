package com.recipe.recipeService.client;

import com.recipe.recipeService.dto.CreateRecipeIngredientRequest;
import com.recipe.recipeService.dto.ResponseDTO;
import com.recipe.recipeService.dto.ViewRecipeIngredientRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "ingredient-service",path = "/api")
public interface IngredientServiceClient {

    @PostMapping("recipe-ingredient")
    ResponseEntity<ResponseDTO<ViewRecipeIngredientRequest>> saveRecipeIngredient(@RequestBody CreateRecipeIngredientRequest request);

    @GetMapping("/recipe-ingredient/{id}")
    ResponseEntity<ResponseDTO<ViewRecipeIngredientRequest>> getRecipeIngredients(@PathVariable(name = "id") Long id);
}
