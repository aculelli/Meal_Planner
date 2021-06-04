package com.techelevator.dao;

import com.techelevator.exceptions.RecipeIngredientNotFoundException;
import com.techelevator.exceptions.RecipeNotFoundException;
import com.techelevator.model.Recipe;
import com.techelevator.model.RecipeIngredient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class JDBCRecipeDAO implements RecipeDAO{

    @Autowired
    RecipeIngredientDAO recipeIngredientDAO;

    private JdbcTemplate jdbcTemplate;

    public JDBCRecipeDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.recipeIngredientDAO = new JDBCRecipeIngredientDAO(jdbcTemplate);
    }

    @Override
    public List<Recipe> getMyRecipes(long userId) {
        List<Recipe> recipesList = new ArrayList<>();
        String sql = "SELECT recipe_id, user_id, name, directions, servings FROM recipes WHERE user_id = ?";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, userId);
        recipesList = mapResultsToRecipes(results);
        return recipesList;
    }

    @Override
    public Recipe getRecipeById(long recipeId) throws RecipeNotFoundException {
        String sql = "SELECT recipe_id, user_id, name, directions, servings FROM recipes WHERE recipe_id = ?";
        SqlRowSet result = jdbcTemplate.queryForRowSet(sql, recipeId);
        if(result.next()) {
            return mapRowToRecipe(result);
        } else {
            throw new RecipeNotFoundException();
        }
    }

    @Override
    public void addRecipe(Recipe recipe) {
        String sqlInsert = "INSERT INTO recipes (name, directions, user_id, servings)" +
                " VALUES(?, ?, ?, ?)";
        jdbcTemplate.update(sqlInsert, recipe.getName(), recipe.getDirections(),
                recipe.getUserId(), recipe.getNumberOfServings());

        String sqlGetRecipeId = "SELECT MAX(recipe_id) FROM recipes WHERE user_id = ? " +
                "AND name = ?";
        long recipeId = jdbcTemplate.queryForObject(sqlGetRecipeId,Long.class, recipe.getUserId(), recipe.getName());
        List<RecipeIngredient> recipeIngredients = recipe.getRecipeIngredients();
        for (RecipeIngredient ingredient : recipeIngredients) {
            ingredient.setRecipeId(recipeId);
            recipeIngredientDAO.addRecipeIngredient(ingredient);
        }
    }

    @Override
    public void addRecipeToMeal(long recipeId, long mealId) {
        String sqlInsert = "INSERT INTO meals_recipes (recipe_id, meal_id)" +
                " VALUES(?, ?)";
        jdbcTemplate.update(sqlInsert, recipeId, mealId);
    }


    @Override
    public void updateRecipe(Recipe recipe) {
        List<RecipeIngredient> recipeIngredients = recipe.getRecipeIngredients();
        //Delete existing recipe ingredients
        String sqlDelete = "DELETE from recipe_ingredients WHERE recipe_id = ?";
        jdbcTemplate.update(sqlDelete, recipe.getRecipeId());

        for (RecipeIngredient ingredient : recipeIngredients) {
            recipeIngredientDAO.addRecipeIngredient(ingredient);
        }
        String sqlInsert = "UPDATE recipes SET name = ?, directions = ?, servings = ?" +
                " WHERE recipe_id = ?";
        jdbcTemplate.update(sqlInsert, recipe.getName(), recipe.getDirections(),
                recipe.getNumberOfServings(), recipe.getRecipeId());

    }

    @Override
    public void removeRecipeFromMeal(long recipeId, long mealId) {
        String sql = "DELETE FROM meals_recipes WHERE recipe_id = ? AND meal_id = ?";
        jdbcTemplate.update(sql, recipeId, mealId);
    }

    @Override
    public void deleteRecipe(long recipeId) {
        String sqlDelete = "DELETE FROM meals_recipes WHERE recipe_id = ?";
        jdbcTemplate.update(sqlDelete, recipeId);
        String sqlDeleteRecIng = "DELETE FROM recipe_ingredients WHERE recipe_id = ?";
        jdbcTemplate.update(sqlDeleteRecIng, recipeId);
        String sql = "DELETE FROM recipes WHERE recipe_id = ?";
        jdbcTemplate.update(sql, recipeId);

    }



    private Recipe mapRowToRecipe(SqlRowSet result) {
        long id = result.getLong("recipe_id");
        String name = result.getString("name");
        String directions = result.getString("directions");
        List<RecipeIngredient> recipeIngredients = recipeIngredientDAO.getRecipeIngredientsByRecipe(id);
        long userId = result.getLong("user_id");
        double numberOfServings = result.getDouble("servings");

        Recipe recipe = new Recipe(id, name, directions, recipeIngredients, userId, numberOfServings);

        return recipe;
    }

    private List<Recipe> mapResultsToRecipes(SqlRowSet results) {
        List<Recipe> recipeList = new ArrayList<>();
        while(results.next()) {
            recipeList.add(mapRowToRecipe(results));
        }
        return recipeList;
    }
}
