package com.techelevator.model;

public class RecipeIngredientAPI {

    private String ingredientName;
    private double measurementQuantity;
    private String measurementType;


    public RecipeIngredientAPI(String ingredientName, double measurementQuantity, String measurementType) {
        this.ingredientName = ingredientName;
        this.measurementQuantity = measurementQuantity;
        this.measurementType = measurementType;
    }

    public String getIngredientName() {
        return ingredientName;
    }

    public void setIngredientName(String ingredientName) {
        this.ingredientName = ingredientName;
    }

    public double getMeasurementQuantity() {
        return measurementQuantity;
    }

    public void setMeasurementQuantity(double measurementQuantity) {
        this.measurementQuantity = measurementQuantity;
    }

    public String getMeasurementType() {
        return measurementType;
    }

    public void setMeasurementType(String measurementType) {
        this.measurementType = measurementType;
    }
}
