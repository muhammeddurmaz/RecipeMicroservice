package com.Recipe.recipesearchservice.service.impl;

import com.Recipe.recipesearchservice.model.ViewRecipeDetail;
import com.Recipe.recipesearchservice.repository.RecipeSearchRepository;
import com.Recipe.recipesearchservice.service.RecipeSearchService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Service
@Transactional
public class RecipeSearchServiceImpl implements RecipeSearchService {
    private final Logger logger = LoggerFactory.getLogger(RecipeSearchServiceImpl.class);
    private final RecipeSearchRepository recipeSearchRepository;

    public RecipeSearchServiceImpl(RecipeSearchRepository recipeSearchRepository) {
        this.recipeSearchRepository = recipeSearchRepository;
    }

    @Override
    public ViewRecipeDetail save(ViewRecipeDetail recipe) {
        ViewRecipeDetail saved = null;
        if(Objects.nonNull(recipe) && recipe.getId() != null){
            try {
                saved = recipeSearchRepository.save(recipe);
            }catch (Exception e){
                return saved;
            }
        }else {
            return saved;
        }
        return saved;
    }

    @Override
    @Transactional(readOnly = true)
    public List<ViewRecipeDetail> search(String query) {
        return recipeSearchRepository.findByNameCustom(query);
    }

    @Override
    public Page<ViewRecipeDetail> findAll() {
        return recipeSearchRepository.findAll(Pageable.unpaged());
    }


}
