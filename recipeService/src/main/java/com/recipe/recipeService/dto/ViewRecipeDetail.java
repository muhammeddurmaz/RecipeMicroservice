package com.recipe.recipeService.dto;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

public class ViewRecipeDetail implements Serializable {
    private Long id;
    private String name;
    private List<RecipeIngredientAmount> ingredients;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<RecipeIngredientAmount> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<RecipeIngredientAmount> ingredients) {
        this.ingredients = ingredients;
    }

    @Override
    public String toString() {
        return "ViewRecipeDetail{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", ingredients=" + ingredients +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ViewRecipeDetail that = (ViewRecipeDetail) o;
        return Objects.equals(id, that.id) && Objects.equals(name, that.name) && Objects.equals(ingredients, that.ingredients);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, ingredients);
    }
}
