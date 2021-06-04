package com.techelevator.controller;

import com.techelevator.dao.RecipeIngredientDAO;
import com.techelevator.exceptions.RecipeIngredientNotFoundException;
import com.techelevator.model.Ingredient;
import com.techelevator.model.MeasurementType;
import com.techelevator.model.RecipeIngredient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static com.techelevator.controller.RecipeController.logTimestamp;

@RestController
@CrossOrigin
@PreAuthorize("isAuthenticated()")
public class RecipeIngredientController {

    @Autowired
    RecipeIngredientDAO recipeIngredientDAO;

    @RequestMapping(path= "/ingredients", method = RequestMethod.GET)
    public List<Ingredient> getAllIngredients() {
        logTimestamp("Getting all ingredients");
        return recipeIngredientDAO.getAllIngredients();
    }

    @RequestMapping(path= "/measurements", method = RequestMethod.GET)
    public List<MeasurementType> getAllMeasurements() {
        logTimestamp("Getting all measurement types");
        return recipeIngredientDAO.getAllMeasurementTypes();
    }

    @RequestMapping(path="/recipe-ingredient/{recipeId}", method = RequestMethod.GET)
    public List<RecipeIngredient> getRecipeIngredientsByRecipeId(@PathVariable long recipeId) {
        logTimestamp("Getting recipe ingredients");
        return recipeIngredientDAO.getRecipeIngredientsByRecipe(recipeId);
    }

    @RequestMapping(path="/recipe-ingredient/{recipeIngredientId}", method = RequestMethod.GET)
    public RecipeIngredient getRecipeIngredientByIngredientId(@PathVariable long recipeIngredientId) throws RecipeIngredientNotFoundException {
        logTimestamp("Getting recipe ingredient");
        return recipeIngredientDAO.getRecipeIngredientById(recipeIngredientId);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(path="/new-recipe-ingredient", method = RequestMethod.POST)
    public void addNewRecipeIngredient(@Valid @RequestBody RecipeIngredient recipeIngredient) {
        recipeIngredientDAO.addRecipeIngredient(recipeIngredient);
        logTimestamp("Adding new recipe ingredient");
    }

    @RequestMapping(path="/update-recipe-ingredient/{recipeIngredientId}", method = RequestMethod.PUT)
    public void editRecipeIngredient(@PathVariable long recipeIngredientId, @Valid @RequestBody RecipeIngredient recipeIngredient) {
        recipeIngredientDAO.updateRecipeIngredient(recipeIngredient);
        logTimestamp("Updating recipe ingredient");
    }

    @RequestMapping(path="/delete-recipe-ingredient/{recipeIngredientId}", method = RequestMethod.DELETE)
    public void deleteRecipeIngredient(@PathVariable long recipeIngredientId) {
        recipeIngredientDAO.deleteRecipeIngredient(recipeIngredientId);
        logTimestamp("Deleting recipe ingredient");
    }

    @RequestMapping(path="/measurement-types", method = RequestMethod.GET)
    public List<MeasurementType> getAllMeasurementTypes() {
        logTimestamp("Getting measurement types");
        return recipeIngredientDAO.getAllMeasurementTypes();
    }
}
