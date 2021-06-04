package com.techelevator.dao;

import com.techelevator.exceptions.MealNotFoundException;
import com.techelevator.exceptions.RecipeNotFoundException;
import com.techelevator.model.Meal;

import java.util.List;

public interface MealDAO {

    public List<Meal> getMealsByDailyPlan(long dailyPlanId) throws MealNotFoundException, RecipeNotFoundException;

    public Meal getMealById(long mealId) throws MealNotFoundException, RecipeNotFoundException;

    public Integer addMeal(Meal meal);

    public Integer updateMeal(Meal meal);

    public Integer deleteMeal(long mealId);



}
