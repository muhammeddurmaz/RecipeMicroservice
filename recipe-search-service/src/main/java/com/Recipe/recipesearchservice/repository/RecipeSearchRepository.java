package com.Recipe.recipesearchservice.repository;

import com.Recipe.recipesearchservice.model.ViewRecipeDetail;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

public interface RecipeSearchRepository extends ElasticsearchRepository<ViewRecipeDetail,Long> {

    @Query("{\"match\":{\"name\":{\"query\":\"?0\", \"fuzziness\":\"AUTO\"}}}")
    List<ViewRecipeDetail> findByNameCustom(String name);

}
