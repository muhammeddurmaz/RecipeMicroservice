package com.recipe.ingredientservice.dto.mapper;

import com.recipe.ingredientservice.dto.CategoryDTO;
import com.recipe.ingredientservice.model.Category;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CategoryMapper extends EntityMapper<CategoryDTO, Category>{
}
