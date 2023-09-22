package com.Recipe.recipesearchservice.controller;

import com.Recipe.recipesearchservice.model.ViewRecipeDetail;
import com.Recipe.recipesearchservice.service.RecipeSearchService;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/recipe-search")
public class RecipeSearchController {

    private final RecipeSearchService recipeSearchService;

    public RecipeSearchController(RecipeSearchService recipeSearchService) {
        this.recipeSearchService = recipeSearchService;
    }

    @PostMapping("")
    public ResponseEntity<ViewRecipeDetail> save(@RequestBody ViewRecipeDetail recipe){
        ViewRecipeDetail savedRecipe = recipeSearchService.save(recipe);
        return ResponseEntity.ok().body(savedRecipe);
    }

    @GetMapping("/{query}")
        public ResponseEntity<List<ViewRecipeDetail>> search(@PathVariable(name = "query") String query){
        List<ViewRecipeDetail> savedRecipe = recipeSearchService.search(query);
        return ResponseEntity.ok().body(savedRecipe);
    }

    @GetMapping("")
    public ResponseEntity<Page<ViewRecipeDetail>> findAll(){
        Page<ViewRecipeDetail> savedRecipe = recipeSearchService.findAll();
        return ResponseEntity.ok().body(savedRecipe);
    }
}
