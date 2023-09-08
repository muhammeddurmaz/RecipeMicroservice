package com.recipe.ingredientservice.controller;

import com.recipe.ingredientservice.dto.*;
import com.recipe.ingredientservice.service.RecipeIngredientService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class RecipeIngredientController {

    private static final String ENTITY_NAME = "RecipeIngredientEntity";
    private final RecipeIngredientService recipeIngredientService;

    public RecipeIngredientController(RecipeIngredientService recipeIngredientService) {
        this.recipeIngredientService = recipeIngredientService;
    }

    @PostMapping("/recipe-ingredient")
    public ResponseEntity<ResponseDTO<ViewRecipeIngredientRequest>> save(@RequestBody CreateRecipeIngredientRequest request){
        ViewRecipeIngredientRequest savedEntity = recipeIngredientService.saveRecipeIngredient(request);
        ResponseDTO<ViewRecipeIngredientRequest> responseDTO = new ResponseDTO<>();
        responseDTO.setData(savedEntity);
        responseDTO.setMessage("Create Success",ENTITY_NAME);
        responseDTO.setSuccess(true);
        return ResponseEntity.ok().body(responseDTO);
    }

    @GetMapping("/recipe-ingredient/{id}")
    public ResponseEntity<ResponseDTO<ViewRecipeIngredientRequest>> getRecipeIngredientByRecipeId(@PathVariable(value = "id") Long id){
        ViewRecipeIngredientRequest ingredientDTO = recipeIngredientService.getRecipeIngredients(id);
        ResponseDTO<ViewRecipeIngredientRequest>  responseDTO = new ResponseDTO<>();
        responseDTO.setData(ingredientDTO);
        responseDTO.setMessage("Get One Entity Success",ENTITY_NAME);
        responseDTO.setSuccess(true);
        return ResponseEntity.ok().body(responseDTO);
    }
}
