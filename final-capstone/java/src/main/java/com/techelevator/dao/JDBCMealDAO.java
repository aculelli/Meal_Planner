package com.techelevator.dao;

import com.techelevator.exceptions.MealNotFoundException;
import com.techelevator.exceptions.RecipeNotFoundException;
import com.techelevator.model.Meal;
import com.techelevator.model.Recipe;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class JDBCMealDAO implements MealDAO {

    @Autowired
    RecipeDAO recipeDAO;

    private JdbcTemplate jdbcTemplate;

    public JDBCMealDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.recipeDAO = new JDBCRecipeDAO(jdbcTemplate);
    }

    @Override
    public List<Meal> getMealsByDailyPlan(long dailyPlanId) throws MealNotFoundException, RecipeNotFoundException {
        List<Meal> mealList = new ArrayList<>();
        String sql = "SELECT meal_id FROM meals WHERE dp_id = ?";
        SqlRowSet result = jdbcTemplate.queryForRowSet(sql, dailyPlanId);
        while (result.next()) {
            long mealId = result.getLong("meal_id");
            mealList.add(getMealById(mealId));
        }
        return mealList;
    }

    @Override
    public Meal getMealById(long mealIdParam) throws MealNotFoundException, RecipeNotFoundException {
        Meal meal = null;
        String sql = "SELECT meal_id, dp_id, meal_type FROM meals m JOIN meal_type mt ON m.meal_type_id " +
                "= mt.meal_type_id WHERE m.meal_id = ?";
        SqlRowSet result = jdbcTemplate.queryForRowSet(sql, mealIdParam);
        if (result.next()) {
            long mealId = result.getLong("meal_id");
            long dpId = result.getLong("dp_id");
            String mealType = result.getString("meal_type");
            List<Recipe> recipeList = new ArrayList<>();
            String sqlRecipes = "SELECT recipe_id FROM meals_recipes WHERE meal_id = ?";
            SqlRowSet resultRecipes = jdbcTemplate.queryForRowSet(sqlRecipes, mealId);
            while (resultRecipes.next()) {
                long recipe_id = resultRecipes.getLong("recipe_id");
                Recipe recipe = recipeDAO.getRecipeById(recipe_id);
                recipeList.add(recipe);
                System.out.println(recipeList.size());
            }
            meal = new Meal(mealId, dpId, mealType, recipeList);
        }
        return meal;
    }

    @Override
    public Integer addMeal(Meal meal) {
        String selectSql = "SELECT meal_type_id FROM meal_type WHERE meal_type = ?";
        Integer mealTypeId = jdbcTemplate.queryForObject(selectSql, Integer.class, meal.getMealType());
        String sql = "INSERT INTO meals (dp_id, meal_type_id) VALUES (?, ?)";
        return jdbcTemplate.update(sql, meal.getDailyPlanId(), mealTypeId);
    }

    @Override
    public Integer updateMeal(Meal meal) {
        String selectSql = "SELECT meal_type_id FROM meal_type WHERE meal_type = ?";
        Integer mealTypeId = jdbcTemplate.queryForObject(selectSql, Integer.class, meal.getMealType());
        String sql = "UPDATE meals SET dp_id = ?, meal_type_id = ? WHERE meal_id = ?";
        return jdbcTemplate.update(sql, meal.getDailyPlanId(), mealTypeId, meal.getMealId());
    }

    @Override
    public Integer deleteMeal(long mealId) {
        String sqlMealsRecipes = "DELETE FROM meals_recipes WHERE meal_id = ?";
        jdbcTemplate.update(sqlMealsRecipes, mealId);
        String sql = "DELETE FROM meals WHERE meal_id = ?";
        return jdbcTemplate.update(sql, mealId);
    }



}
