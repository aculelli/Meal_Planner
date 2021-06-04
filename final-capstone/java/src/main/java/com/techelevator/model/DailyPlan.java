package com.techelevator.model;

import java.util.List;

public class DailyPlan {
    private long dpId;
    private String weekday;
    private List<Meal> meals;
    private long mealPlanId;

    public DailyPlan(long dpId, String weekday, List<Meal> meals, long mealPlanId) {
        this.dpId = dpId;
        this.weekday = weekday;
        this.meals = meals;
        this.mealPlanId = mealPlanId;
    }

    public long getDpId() {
        return dpId;
    }

    public void setDpId(long dpId) {
        this.dpId = dpId;
    }

    public String getWeekday() {
        return weekday;
    }

    public void setWeekday(String weekday) {
        this.weekday = weekday;
    }

    public List<Meal> getMeals() {
        return meals;
    }

    public void setMeals(List<Meal> meals) {
        this.meals = meals;
    }

    public long getMealPlanId() {
        return mealPlanId;
    }

    public void setMealPlanId(long mealPlanId) {
        this.mealPlanId = mealPlanId;
    }
}
