package com.techelevator.controller;

import com.techelevator.dao.DailyPlanDAO;
import com.techelevator.dao.MealPlanDAO;
import com.techelevator.dao.UserDAO;
import com.techelevator.exceptions.MealNotFoundException;
import com.techelevator.exceptions.MealPlanNotFoundException;
import com.techelevator.exceptions.RecipeNotFoundException;
import com.techelevator.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import static com.techelevator.controller.RecipeController.logTimestamp;

@RestController
@CrossOrigin
//@PreAuthorize("isAuthenticated()")
public class MealPlanController {
    @Autowired
    MealPlanDAO mealPlanDAO;

    @Autowired
    DailyPlanDAO dailyPlanDAO;

    @Autowired
    UserDAO userDAO;

    @RequestMapping(path="/meal-plans", method = RequestMethod.GET)
    public List<MealPlan> getMyMealPlans(Principal principal) throws MealNotFoundException, RecipeNotFoundException {
        logTimestamp("Getting my meal plans");
        String username = principal.getName();
        long userId = userDAO.findIdByUsername(username);
        return mealPlanDAO.getAllMealPlansByUserId(userId);
    }

    @RequestMapping(path="my-meal-plan", method = RequestMethod.GET)
    public MealPlan getMyMealPlan(Principal principal, @RequestParam String mealPlanName) throws MealPlanNotFoundException, RecipeNotFoundException, MealNotFoundException {
        String username = principal.getName();
        long userId = userDAO.findIdByUsername(username);
        return mealPlanDAO.getMealPlanByName(userId, mealPlanName);

    }

    @RequestMapping(path="/meal-plans/{mealPlanId}", method = RequestMethod.GET)
    public MealPlan getMealPlanById(@PathVariable long mealPlanId) throws MealPlanNotFoundException, MealNotFoundException, RecipeNotFoundException {
        logTimestamp("Retrieving meal plan");
        return mealPlanDAO.getMealPlanById(mealPlanId);
    }

    @RequestMapping(path="/grocery-list/{mealPlanId}", method = RequestMethod.GET)
    public List<GroceryListItem> getIngredientsByMealPlanId(@PathVariable long mealPlanId)  {
        logTimestamp("Retrieving grocery list");
        return mealPlanDAO.getIngredientsByMealPlanId(mealPlanId);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(path="/add-new-meal-plan", method = RequestMethod.POST)
    public void addNewMealPlan(@Valid @RequestBody MealPlan mealPlan) {
        Long mealPlanId = mealPlanDAO.addMealPlan(mealPlan);
        String[] weekdays = {"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"};
        String[] mealTypes = {"Breakfast", "Lunch", "Dinner", "Dessert"};
        List<Meal> meals = new ArrayList<>();
        List<Recipe> recipes = new ArrayList<>();
        for(String mealType : mealTypes) {
            Meal meal = new Meal(0, 0, mealType, recipes);
            meals.add(meal);
        }
        for(String weekday : weekdays){
            DailyPlan dailyPlan = new DailyPlan(0, weekday, meals, mealPlanId);
            dailyPlanDAO.addDailyPlanToMealPlan(dailyPlan);
        }
        logTimestamp("Adding new meal plan");
    }

    @RequestMapping(path="/update-meal-plan/{mealPlanId}", method = RequestMethod.PUT)
    public void editMealPlan(@PathVariable long mealPlanId, @Valid @RequestBody MealPlan mealPlan) throws RecipeNotFoundException, MealNotFoundException {
        mealPlanDAO.updateMealPlan(mealPlan);
        logTimestamp("Updating meal plan");
    }

    @RequestMapping(path="/delete-meal-plan/{mealPlanId}", method = RequestMethod.DELETE)
    public void deleteMealPlan(@PathVariable long mealPlanId) throws MealNotFoundException, RecipeNotFoundException {
        mealPlanDAO.deleteMealPlan(mealPlanId);
        logTimestamp("Deleting meal plan from database");
    }
}
