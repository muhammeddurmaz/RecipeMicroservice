package com.recipe.recipeService.client;

import com.recipe.recipeService.dto.ViewRecipeDetail;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "recipe-search-service",path = "/recipe-search")
public interface RecipeSearchServiceClient {

    @PostMapping("")
    ResponseEntity<ViewRecipeDetail> saveRecipeSearch(@RequestBody ViewRecipeDetail request);
}
