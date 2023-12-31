package com.recipe.recipeService.controller;

import com.recipe.recipeService.dto.*;
import com.recipe.recipeService.service.RecipeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class RecipeController {
    private static final String ENTITY_NAME = "RecipeEntity";
    private final RecipeService recipeService;

    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @PostMapping("/recipe")
    public ResponseEntity<ResponseDTO<ViewRecipeDetail>> save(@RequestBody CreateRecipeRequest createRecipeRequest){
        ViewRecipeDetail savedEntity = recipeService.save(createRecipeRequest);
        ResponseDTO<ViewRecipeDetail> responseDTO = new ResponseDTO<>();
        responseDTO.setData(savedEntity);
        responseDTO.setMessage("Create Success",ENTITY_NAME);
        responseDTO.setSuccess(true);
        return ResponseEntity.ok().body(responseDTO);
    }

    @GetMapping("/recipe/{id}")
    public ResponseEntity<ResponseDTO<RecipeDTO>> getRecipeId(@PathVariable(value = "id") Long id){
        RecipeDTO recipeDTO = recipeService.findOneById(id);
        ResponseDTO<RecipeDTO>  responseDTO = new ResponseDTO<>();
        responseDTO.setData(recipeDTO);
        responseDTO.setMessage("Get One Entity Success",ENTITY_NAME);
        responseDTO.setSuccess(true);
        return ResponseEntity.ok().body(responseDTO);
    }
}
