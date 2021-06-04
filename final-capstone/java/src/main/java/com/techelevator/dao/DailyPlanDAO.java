package com.techelevator.dao;

import com.techelevator.exceptions.DailyPlanNotFoundException;
import com.techelevator.exceptions.MealNotFoundException;
import com.techelevator.exceptions.RecipeNotFoundException;
import com.techelevator.model.DailyPlan;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import java.util.List;


public interface DailyPlanDAO {

    public List<DailyPlan> getDailyPlansByMealPlanId(long mealPlanId) throws MealNotFoundException, RecipeNotFoundException;

    public DailyPlan getDailyPlanByDailyPlanId(long dpId) throws MealNotFoundException, RecipeNotFoundException, DailyPlanNotFoundException;

    public void addDailyPlanToMealPlan(DailyPlan dailyPlan);

    public void updateDailyPlan(DailyPlan dailyPlan) throws RecipeNotFoundException, MealNotFoundException;

    public void deleteDailyPlan(long id);

    public DailyPlan mapRowToDailyPlan(SqlRowSet result) throws RecipeNotFoundException, MealNotFoundException;

    public List<DailyPlan> mapResultsToDailyPlanList(SqlRowSet results) throws MealNotFoundException, RecipeNotFoundException;
}
