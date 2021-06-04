package com.techelevator.model;

import javax.validation.constraints.NotNull;
import java.util.List;

public class Meal {
    private long mealId;
    @NotNull
    private long dailyPlanId;
    @NotNull
    private String mealType;
    private List<Recipe> recipes;

    public Meal(long mealId, long dailyPlanId, String mealType, List<Recipe> recipes) {
        this.mealId = mealId;
        this.dailyPlanId = dailyPlanId;
        this.mealType = mealType;
        this.recipes = recipes;
    }

    public long getMealId() {
        return mealId;
    }

    public void setMealId(long mealId) {
        this.mealId = mealId;
    }

    public long getDailyPlanId() {
        return dailyPlanId;
    }

    public void setDailyPlanId(long dailyPlanId) {
        this.dailyPlanId = dailyPlanId;
    }

    public String getMealType() {
        return mealType;
    }

    public void setMealType(String mealType) {
        this.mealType = mealType;
    }

    public List<Recipe> getRecipes() {
        return recipes;
    }

    public void setRecipes(List<Recipe> recipes) {
        this.recipes = recipes;
    }

    @Override
    public String toString() {
        return "Meal{" +
                "mealId=" + mealId +
                ", dailyPlanId=" + dailyPlanId +
                ", mealType='" + mealType + '\'' +
                ", recipes=" + recipes +
                '}';
    }
}
