package com.recipe.ingredientservice.dto.mapper;

import com.recipe.ingredientservice.dto.CategoryDTO;
import com.recipe.ingredientservice.dto.IngredientDTO;
import com.recipe.ingredientservice.model.Category;
import com.recipe.ingredientservice.model.Ingredient;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public interface IngredientMapper extends EntityMapper<IngredientDTO, Ingredient>{

//    @Mapping(target = "category", source = "category", qualifiedByName = "categoryId")
//    IngredientDTO toDto(Ingredient s);
//
//    @Named("categoryId")
//    @BeanMapping(ignoreByDefault = true)
//    @Mapping(target = "id", source = "id")
//    CategoryDTO toDtoCategoryId(Category category);
}
