package com.recipe.ingredientservice.model;

import javax.persistence.*;
import java.util.Objects;


@Table(name = "recipe_ingredient")
@Entity
public class RecipeIngredient {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "recipe_id")
    private Long recipeId;
    @Column(name = "amount")
    private String amount;
    @ManyToOne
    @JoinColumn
    private Ingredient ingredient;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getRecipeId() {
        return recipeId;
    }

    public void setRecipeId(Long recipeId) {
        this.recipeId = recipeId;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public Ingredient getIngredient() {
        return ingredient;
    }

    public void setIngredient(Ingredient ingredient) {
        this.ingredient = ingredient;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RecipeIngredient that = (RecipeIngredient) o;
        return Objects.equals(id, that.id) && Objects.equals(recipeId, that.recipeId) && Objects.equals(amount, that.amount) && Objects.equals(ingredient, that.ingredient);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, recipeId, amount, ingredient);
    }

    @Override
    public String toString() {
        return "RecipeIngredient{" +
                "id=" + id +
                ", recipeId=" + recipeId +
                ", amount='" + amount + '\'' +
                ", ingredient=" + ingredient +
                '}';
    }
}
