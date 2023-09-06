package com.recipe.ingredientservice.dto;

import java.io.Serializable;
import java.util.Objects;

public class CreateIngredientRequest implements Serializable {
    private String name;

    private CategoryDTO category;
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public CategoryDTO getCategory() {
        return category;
    }

    public void setCategory(CategoryDTO category) {
        this.category = category;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CreateIngredientRequest that = (CreateIngredientRequest) o;
        return Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
