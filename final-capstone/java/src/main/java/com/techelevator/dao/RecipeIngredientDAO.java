package com.techelevator.dao;

import com.techelevator.exceptions.RecipeIngredientNotFoundException;
import com.techelevator.model.Ingredient;
import com.techelevator.model.MeasurementType;
import com.techelevator.model.RecipeIngredient;

import java.util.List;

public interface RecipeIngredientDAO {

    public List<Ingredient> getAllIngredients();

    public List<MeasurementType> getAllMeasurementTypes();

    public List<RecipeIngredient> getRecipeIngredientsByRecipe(long recipeId);

    public RecipeIngredient getRecipeIngredientById(long recipeIngredientId) throws RecipeIngredientNotFoundException;

    public void addRecipeIngredient(RecipeIngredient recipeIngredient);

    public void updateRecipeIngredient(RecipeIngredient recipeIngredient);

    public void deleteRecipeIngredient(long recipeId);

}
