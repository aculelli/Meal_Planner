package com.techelevator.controller;

import com.techelevator.dao.RecipeDAO;
import com.techelevator.dao.UserDAO;
import com.techelevator.exceptions.RecipeNotFoundException;
import com.techelevator.model.Recipe;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.sql.Timestamp;
import java.util.List;

@RestController
@PreAuthorize("isAuthenticated()")
@CrossOrigin
public class RecipeController {

    @Autowired
    RecipeDAO recipeDAO;

    @Autowired
    UserDAO userDAO;

    @RequestMapping(path = "/my-recipes", method = RequestMethod.GET)
    public List<Recipe> returnMyRecipes(Principal principal) {
        logTimestamp("Getting my recipes");
        String username = principal.getName();
        long userId = userDAO.findIdByUsername(username);

        List<Recipe> myRecipes = recipeDAO.getMyRecipes(userId);
        return myRecipes;
    }

    @RequestMapping(path = "/my-recipes/{recipeId}", method = RequestMethod.GET)
    public Recipe returnRecipeById(@PathVariable long recipeId) throws RecipeNotFoundException {
        logTimestamp("Getting my recipe details");
        Recipe myRecipe = recipeDAO.getRecipeById(recipeId);
        return myRecipe;
    }

    @RequestMapping(path = "/new-recipe", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public void addNewRecipe(@RequestBody Recipe recipe) {
        recipeDAO.addRecipe(recipe);
        logTimestamp("Adding new recipe");
    }

    @RequestMapping(path = "/update-recipe/{recipeId}", method = RequestMethod.PUT)
    public void editRecipe(@PathVariable long recipeId, @RequestBody Recipe recipe) {
        recipeDAO.updateRecipe(recipe);
        logTimestamp("Updating recipe");
    }

    @RequestMapping(path = "/delete-recipe/{recipeId}", method = RequestMethod.DELETE)
    public void deleteRecipe(@PathVariable long recipeId) {
        recipeDAO.deleteRecipe(recipeId);
        logTimestamp("Deleting recipe from database");
    }

    @RequestMapping(path = "/add-recipe-to-meal/{mealId}/recipe/{recipeId}", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public void addRecipeToMeal(@PathVariable long mealId, @PathVariable long recipeId) {
      //  Long recipeID = Long.parseLong(recipeId);
        recipeDAO.addRecipeToMeal(recipeId, mealId);
        logTimestamp("Adding recipe to meal");
    }

    @RequestMapping(path = "/remove-recipe-from-meal/{mealId}/recipe/{recipeId}", method = RequestMethod.DELETE)
    public void removeRecipeFromMeal(@PathVariable long mealId, @PathVariable long recipeId) {
        recipeDAO.removeRecipeFromMeal(recipeId, mealId);
        logTimestamp("Removing recipe from meal");
    }

    static void logTimestamp(String msg) {    // log timestamp of request to Console
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        System.out.println(msg + " at " + timestamp);
    }


}
