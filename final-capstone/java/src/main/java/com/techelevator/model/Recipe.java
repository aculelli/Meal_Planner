package com.techelevator.model;

import java.util.List;

public class Recipe {
    private long recipeId;
    private String name;
    private String directions;
    private List<RecipeIngredient> recipeIngredients;
    private long userId;
    private double numberOfServings;

    public Recipe(long recipeId, String name, String directions, List<RecipeIngredient> recipeIngredients, long userId, double numberOfServings) {
        this.recipeId = recipeId;
        this.name = name;
        this.directions = directions;
        this.recipeIngredients = recipeIngredients;
        this.userId = userId;
        this.numberOfServings = numberOfServings;
    }


    public long getRecipeId() {
        return recipeId;
    }

    public void setRecipeId(long recipeId) {
        this.recipeId = recipeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDirections() {
        return directions;
    }

    public void setDirections(String directions) {
        this.directions = directions;
    }

    public List<RecipeIngredient> getRecipeIngredients() {
        return recipeIngredients;
    }

    public void setRecipeIngredients(List<RecipeIngredient> recipeIngredients) {
        this.recipeIngredients = recipeIngredients;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public double getNumberOfServings() {
        return numberOfServings;
    }

    public void setNumberOfServings(double numberOfServings) {
        this.numberOfServings = numberOfServings;
    }

    @Override
    public String toString() {
        return "Recipe{" +
                "recipeId=" + recipeId +
                ", name='" + name + '\'' +
                ", directions='" + directions + '\'' +
                ", recipeIngredients=" + recipeIngredients +
                '}';
    }
}
