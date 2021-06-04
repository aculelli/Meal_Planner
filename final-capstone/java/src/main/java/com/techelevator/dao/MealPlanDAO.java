package com.techelevator.dao;

import com.techelevator.exceptions.MealNotFoundException;
import com.techelevator.exceptions.MealPlanNotFoundException;
import com.techelevator.exceptions.RecipeNotFoundException;
import com.techelevator.model.DailyPlan;
import com.techelevator.model.GroceryListItem;
import com.techelevator.model.MealPlan;

import java.util.List;

public interface MealPlanDAO {

    public List<MealPlan> getAllMealPlansByUserId(long userId) throws RecipeNotFoundException, MealNotFoundException;

    public MealPlan getMealPlanByName(long userId, String mealName) throws RecipeNotFoundException, MealNotFoundException, MealPlanNotFoundException;

    public MealPlan getMealPlanById(long mealPlanId) throws MealNotFoundException, RecipeNotFoundException, MealPlanNotFoundException;

    public List<GroceryListItem> getIngredientsByMealPlanId(long mealPlanId);

    public Long addMealPlan(MealPlan mealPlan);

    public void updateMealPlan(MealPlan mealPlan) throws MealNotFoundException, RecipeNotFoundException;

    public void deleteMealPlan(long mealPlanId) throws MealNotFoundException, RecipeNotFoundException;

}
