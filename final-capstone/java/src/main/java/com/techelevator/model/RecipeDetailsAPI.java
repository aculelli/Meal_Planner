package com.techelevator.model;

import java.util.List;

public class RecipeDetailsAPI {

    private long recipeId;
    private String name;
    private String directions;
    private List<RecipeIngredientAPI> recipeIngredientsAPI;
    private double numberOfServings;

    public RecipeDetailsAPI(long recipeId, String name, String directions, List<RecipeIngredientAPI> recipeIngredientsAPI, double numberOfServings) {
        this.recipeId = recipeId;
        this.name = name;
        this.directions = directions;
        this.recipeIngredientsAPI = recipeIngredientsAPI;
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

    public List<RecipeIngredientAPI> getRecipeIngredients() {
        return recipeIngredientsAPI;
    }

    public void setRecipeIngredients(List<RecipeIngredientAPI> recipeIngredients) {
        this.recipeIngredientsAPI = recipeIngredients;
    }

    public double getNumberOfServings() {
        return numberOfServings;
    }

    public void setNumberOfServings(double numberOfServings) {
        this.numberOfServings = numberOfServings;
    }
}
