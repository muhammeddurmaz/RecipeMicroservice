package com.recipe.ingredientservice.dto.mapper;

import com.recipe.ingredientservice.dto.IngredientDTO;
import com.recipe.ingredientservice.model.Ingredient;
import org.mapstruct.Mapper;

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
