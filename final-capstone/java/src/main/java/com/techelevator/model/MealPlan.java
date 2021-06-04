package com.techelevator.model;

import java.util.List;

public class MealPlan {

    private long mealPlanId;
    private long userId;
    private String mealPlanName;
    private List<DailyPlan> dailyPlanList;

    public MealPlan(long mealPlanId, long userId, String mealPlanName, List<DailyPlan> dailyPlanList) {
        this.mealPlanId = mealPlanId;
        this.userId = userId;
        this.mealPlanName = mealPlanName;
        this.dailyPlanList = dailyPlanList;
    }

    public long getMealPlanId() {
        return mealPlanId;
    }

    public void setMealPlanId(long mealPlanId) {
        this.mealPlanId = mealPlanId;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getMealPlanName() {
        return mealPlanName;
    }

    public void setMealPlanName(String mealPlanName) {
        this.mealPlanName = mealPlanName;
    }

    public List<DailyPlan> getDailyPlanList() {
        return dailyPlanList;
    }

    public void setDailyPlanList(List<DailyPlan> dailyPlanList) {
        this.dailyPlanList = dailyPlanList;
    }
}
