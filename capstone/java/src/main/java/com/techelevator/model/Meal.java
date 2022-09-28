package com.techelevator.model;

import java.time.LocalDate;

public class Meal {

    private int mealId;
    private String mealType;
    private LocalDate mealDate;

    public Meal(int mealId, String mealType, LocalDate mealDate) {
        this.mealId = mealId;
        this.mealType = mealType;
        this.mealDate = mealDate;
    }

    public Meal() {

    }

    public int getMealId() {
        return mealId;
    }

    public void setMealId(int mealId) {
        this.mealId = mealId;
    }

    public String getMealType() {
        return mealType;
    }

    public void setMealType(String mealType) {
        this.mealType = mealType;
    }

    public LocalDate getMealDate() {
        return mealDate;
    }

    public void setMealDate(LocalDate mealDate) {
        this.mealDate = mealDate;
    }
}
