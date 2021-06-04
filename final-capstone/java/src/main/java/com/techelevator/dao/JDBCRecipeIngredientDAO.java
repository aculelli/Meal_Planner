package com.techelevator.dao;

import com.techelevator.exceptions.RecipeIngredientNotFoundException;
import com.techelevator.model.Ingredient;
import com.techelevator.model.MeasurementType;
import com.techelevator.model.Recipe;
import com.techelevator.model.RecipeIngredient;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Component
public class JDBCRecipeIngredientDAO implements RecipeIngredientDAO {

    private JdbcTemplate jdbcTemplate;

    public JDBCRecipeIngredientDAO(JdbcTemplate jdbcTemplate) {

        this.jdbcTemplate = jdbcTemplate;
    }


    @Override
    public List<Ingredient> getAllIngredients() {
        List<Ingredient> ingredientsList;
        String sql = "SELECT * FROM ingredients";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
        ingredientsList = mapResultsToIngredients(results);

        return ingredientsList;
    }

    @Override
    public List<MeasurementType> getAllMeasurementTypes() {
        List<MeasurementType> measurementTypesList;
        String sql = "SELECT * FROM measurement_type";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
        measurementTypesList = mapResultsToMeasurementType(results);

        return measurementTypesList;
    }

    @Override
    public List<RecipeIngredient> getRecipeIngredientsByRecipe(long recipeId) {
        List<RecipeIngredient> recipeIngredientsList = new ArrayList<>();
        String sql = "SELECT ri.recipe_ingr_id, ri.recipe_id, i.name, " +
                "ri.mt_quantity, mt.mt_name FROM recipe_ingredients ri " +
                "JOIN ingredients i ON ri.ingredient_id = i.ingredient_id " +
                "JOIN measurement_type mt ON ri.mt_type_id = mt.mt_id WHERE ri.recipe_id = ?";
        SqlRowSet result = jdbcTemplate.queryForRowSet(sql, recipeId);
        recipeIngredientsList = mapResultsToRecipeIngredients(result);

        return recipeIngredientsList;
    }

    @Override
    public RecipeIngredient getRecipeIngredientById(long recipeIngredientId) throws RecipeIngredientNotFoundException {

        String sql = "SELECT ri.recipe_ingr_id, ri.recipe_id, i.name, " +
                "ri.mt_quantity, mt.mt_name FROM recipe_ingredients ri " +
                "JOIN ingredients i ON ri.ingredient_id = i.ingredient_id " +
                "JOIN measurement_type mt ON ri.mt_type_id = mt.mt_id " +
                "WHERE ri.recipe_ingr_id = ?";
        SqlRowSet result = jdbcTemplate.queryForRowSet(sql, recipeIngredientId);
        if(result.next()) {
            return mapRowToRecipeIngredient(result);
        } else {
           throw new RecipeIngredientNotFoundException();
        }
    }

    @Override
    public void addRecipeIngredient(RecipeIngredient recipeIngredient) {

        String sqlCheck = "SELECT COALESCE(MAX(ingredient_id), 0) FROM ingredients WHERE lower(name) = ?";
        Long resultIngredientId = jdbcTemplate.queryForObject(sqlCheck, Long.class, recipeIngredient.getIngredientName().toLowerCase());
        String measCheck = "SELECT COALESCE(MAX(mt_id), 0) FROM measurement_type WHERE lower(mt_name) = ?";
        Long resultMeasurementId = jdbcTemplate.queryForObject(measCheck, Long.class, recipeIngredient.getMeasurementType().toLowerCase());

        if (resultIngredientId == 0) {
            resultIngredientId = insertIngredient(recipeIngredient);
        }

        if (resultMeasurementId == 0) {
            resultMeasurementId = insertMeasurement(recipeIngredient);
        }

        String sqlRecIngInsert = "INSERT INTO recipe_ingredients (recipe_id, ingredient_id, mt_type_id, mt_quantity)" +
                " VALUES(?, ?, ?, ?)";
        jdbcTemplate.update(sqlRecIngInsert, recipeIngredient.getRecipeId(), resultIngredientId, resultMeasurementId,
                recipeIngredient.getMeasurementQuantity());

    }

    @Override
    public void updateRecipeIngredient(RecipeIngredient recipeIngredient) {
        String sqlCheck = "SELECT COALESCE(MAX(ingredient_id), 0) FROM ingredients WHERE lower(name) = ?";
        Long resultIngredientId = jdbcTemplate.queryForObject(sqlCheck, Long.class,
                recipeIngredient.getIngredientName().toLowerCase());

        String measCheck = "SELECT COALESCE(MAX(mt_id), 0) FROM measurement_type WHERE lower(mt_name) = ?";
        Long resultMeasurementId = jdbcTemplate.queryForObject(measCheck, Long.class,
                recipeIngredient.getMeasurementType().toLowerCase());

        if (resultIngredientId == 0) {
            resultIngredientId = insertIngredient(recipeIngredient);
        }

        if (resultMeasurementId == 0) {
            resultMeasurementId = insertMeasurement(recipeIngredient);
        }

        String sqlUpdate = "UPDATE recipe_ingredients SET ingredient_id = ?, mt_type_id = ?, mt_quantity = ? " +
                "WHERE recipe_ingr_id = ?";
        jdbcTemplate.update(sqlUpdate, resultIngredientId, resultMeasurementId,
                recipeIngredient.getMeasurementQuantity(), recipeIngredient.getRecipeIngredientId());

    }

    @Override
    public void deleteRecipeIngredient(long recipeIngredientId) {

        String sql = "DELETE FROM recipe_ingredients WHERE recipe_ingr_id = ?";
        jdbcTemplate.update(sql, recipeIngredientId);

    }

    private RecipeIngredient mapRowToRecipeIngredient(SqlRowSet result) {
        long id = result.getLong("recipe_ingr_id");
        long recipeId = result.getLong("recipe_id");
        String ingredientName = result.getString("name");
        double measurementQty = result.getDouble("mt_quantity");
        String measurementType = result.getString("mt_name");

        RecipeIngredient recipeIngredient = new RecipeIngredient
                (id, recipeId, ingredientName, measurementQty, measurementType);

        return recipeIngredient;
    }

    private List<RecipeIngredient> mapResultsToRecipeIngredients(SqlRowSet results) {
        List<RecipeIngredient> recipeIngredients = new ArrayList<>();
        while(results.next()) {
            recipeIngredients.add(mapRowToRecipeIngredient(results));
        }

        return recipeIngredients;
    }

    private Ingredient mapRowToIngredient(SqlRowSet result) {
        long ingredientId = result.getLong("ingredient_id");
        String ingredientName = result.getString("name");
        Ingredient ingredient = new Ingredient(ingredientId, ingredientName);

        return ingredient;
    }

    private List<Ingredient> mapResultsToIngredients(SqlRowSet results) {
        List<Ingredient> ingredients = new ArrayList<>();
        while(results.next()) {
            ingredients.add(mapRowToIngredient(results));
        }
        return ingredients;
    }

    private MeasurementType mapRowToMeasurementType(SqlRowSet result) {
        long measurementTypeId = result.getLong("mt_id");
        String measurementTypeName = result.getString("mt_name");
        return new MeasurementType(measurementTypeId, measurementTypeName);
    }

    private List<MeasurementType> mapResultsToMeasurementType(SqlRowSet results) {
        List<MeasurementType> measurementTypes = new ArrayList<>();
        while(results.next()) {
            measurementTypes.add(mapRowToMeasurementType(results));
        }
        return measurementTypes;
    }

    private Long insertIngredient(RecipeIngredient recipeIngredient) {
        String sqlIngInsert = "INSERT INTO ingredients (name) VALUES (?)";
        jdbcTemplate.update(sqlIngInsert, recipeIngredient.getIngredientName().toLowerCase());
        String sqlIngQuery = "SELECT ingredient_id FROM ingredients WHERE lower(name) = ?";
        Long resultIngredientId = jdbcTemplate.queryForObject(sqlIngQuery, Long.class, recipeIngredient.getIngredientName().toLowerCase());
        return resultIngredientId;
    }

    private Long insertMeasurement(RecipeIngredient recipeIngredient) {
        String sqlMeasInsert = "INSERT INTO measurement_type (mt_name) VALUES (?)";
        jdbcTemplate.update(sqlMeasInsert, recipeIngredient.getMeasurementType().toLowerCase());
        String sqlMeasQuery = "SELECT mt_id FROM measurement_type WHERE lower(mt_name) = ?";
        Long resultMeasurementId = jdbcTemplate.queryForObject(sqlMeasQuery, Long.class, recipeIngredient.getMeasurementType().toLowerCase());
        return resultMeasurementId;
    }



}
