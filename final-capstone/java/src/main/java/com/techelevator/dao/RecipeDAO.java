package com.techelevator.dao;

import com.techelevator.exceptions.RecipeIngredientNotFoundException;
import com.techelevator.exceptions.RecipeNotFoundException;
import com.techelevator.model.Recipe;
import com.techelevator.model.RecipeIngredient;

import java.util.List;

public interface RecipeDAO {

    public List<Recipe> getMyRecipes(long userId);

    public Recipe getRecipeById(long recipeId) throws RecipeNotFoundException;

    public void addRecipe(Recipe recipe);

    public void addRecipeToMeal(long recipeId, long mealId);

    public void updateRecipe(Recipe recipe);

    public void removeRecipeFromMeal(long recipeId, long mealId);

    public void deleteRecipe(long recipeId);

}
