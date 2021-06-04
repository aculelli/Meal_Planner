package com.techelevator.controller;

import com.techelevator.dao.DailyPlanDAO;
import com.techelevator.dao.UserDAO;
import com.techelevator.exceptions.DailyPlanNotFoundException;
import com.techelevator.exceptions.MealNotFoundException;
import com.techelevator.exceptions.RecipeNotFoundException;
import com.techelevator.model.DailyPlan;
import com.techelevator.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.sql.Timestamp;
import java.util.List;

import static com.techelevator.controller.RecipeController.logTimestamp;

@RestController
@PreAuthorize("isAuthenticated()")
@CrossOrigin
public class DailyPlanController {

    @Autowired
    DailyPlanDAO dailyPlanDAO;

    @RequestMapping(path = "/my-daily-plans", method = RequestMethod.GET)
    public List<DailyPlan> returnMyDailyPlans(@RequestParam long mealPlanId) throws MealNotFoundException, RecipeNotFoundException {
        logTimestamp("Getting my daily plans");
        List<DailyPlan> myDailyPlans = dailyPlanDAO.getDailyPlansByMealPlanId(mealPlanId);
        return myDailyPlans;
    }

    @RequestMapping(path = "/daily-plans/{id}", method = RequestMethod.GET)
    public DailyPlan returnDailyPlanById(@PathVariable long id) throws MealNotFoundException, RecipeNotFoundException, DailyPlanNotFoundException {
        logTimestamp("Getting daily plan details");
        DailyPlan myDailyPlan = dailyPlanDAO.getDailyPlanByDailyPlanId(id);
        return myDailyPlan;
    }

    @RequestMapping(path = "/add-new-daily-plan", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public void addDailyPlan(@RequestBody DailyPlan dailyPlan) {
        dailyPlanDAO.addDailyPlanToMealPlan(dailyPlan);
        logTimestamp("Adding new daily plan to meal plan");
    }

    @RequestMapping(path = "/update-daily-plan/{dpId}", method = RequestMethod.PUT)
    public void editDailyPlan(@PathVariable long dpId, @RequestBody DailyPlan dailyPlan) throws RecipeNotFoundException, MealNotFoundException {
        dailyPlanDAO.updateDailyPlan(dailyPlan);
        logTimestamp("Updating daily plan");
    }

    @RequestMapping(path = "/delete-daily-plan/{dpId}", method = RequestMethod.DELETE)
    public void deleteMyDailyPlan(long dpId) {
        dailyPlanDAO.deleteDailyPlan(dpId);
        logTimestamp("Deleting daily plan");
    }



}
