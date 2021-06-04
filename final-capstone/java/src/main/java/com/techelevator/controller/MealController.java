package com.techelevator.controller;

import com.techelevator.dao.MealDAO;
import com.techelevator.exceptions.MealNotFoundException;
import com.techelevator.exceptions.RecipeNotFoundException;
import com.techelevator.model.Meal;

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
public class MealController {
    @Autowired
    MealDAO mealDAO;

    @RequestMapping(path = "/meals/{mealId}", method = RequestMethod.GET)
    public Meal getMealById(@PathVariable long mealId) throws MealNotFoundException, RecipeNotFoundException {
        logTimestamp("Getting meal by id");
        return mealDAO.getMealById(mealId);
    }

    @RequestMapping(path = "/daily-plan/{dpId}/meals", method = RequestMethod.GET)
    public List<Meal> getMealsByDailyPlanId(@PathVariable long dpId) throws MealNotFoundException, RecipeNotFoundException {
        logTimestamp("Getting meals by daily plan id");
        return mealDAO.getMealsByDailyPlan(dpId);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(path = "/new-meal", method = RequestMethod.POST)
    public void addMeal(@Valid @RequestBody Meal meal) {
        mealDAO.addMeal(meal);
        logTimestamp("Adding meal");
    }

    @RequestMapping(path = "/meals/{mealId}", method = RequestMethod.PUT)
    public void updateMeal(@PathVariable long mealId, @Valid @RequestBody Meal meal) {
        mealDAO.updateMeal(meal);
        logTimestamp("Updating meal");
    }

    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    @RequestMapping(path = "/meals/{mealId}", method = RequestMethod.DELETE)
    public void deleteMeal(@PathVariable long mealId) {
        mealDAO.deleteMeal(mealId);
        logTimestamp("Deleting meal");
    }

}
