package com.Recipe.recipesearchservice.model;

import com.Recipe.recipesearchservice.dto.RecipeIngredientDTO;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

@Document(indexName = "recipe")
public class ViewRecipeDetail implements Serializable {
    @Id
    @Field(type = FieldType.Keyword)
    private Long id;
    @Field(type = FieldType.Text , name = "name")
    private String name;
    @Field(type = FieldType.Nested, name = "ingredient")
    private List<RecipeIngredientDTO> ingredients;

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

    public List<RecipeIngredientDTO> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<RecipeIngredientDTO> ingredients) {
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
