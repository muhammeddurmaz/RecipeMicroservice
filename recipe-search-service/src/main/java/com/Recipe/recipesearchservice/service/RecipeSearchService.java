package com.Recipe.recipesearchservice.service;


import com.Recipe.recipesearchservice.model.ViewRecipeDetail;
import org.springframework.data.domain.Page;

import java.util.List;

public interface RecipeSearchService {

    ViewRecipeDetail save(ViewRecipeDetail recipe);

    List<ViewRecipeDetail> search(String query);

    Page<ViewRecipeDetail> findAll();
}
