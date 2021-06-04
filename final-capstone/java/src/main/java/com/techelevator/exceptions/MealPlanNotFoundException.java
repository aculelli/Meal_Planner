package com.techelevator.exceptions;

import com.techelevator.model.MealPlan;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Meal plan not found.")
public class MealPlanNotFoundException extends Exception {
    public MealPlanNotFoundException() { super("Meal plan not found.");}
}
