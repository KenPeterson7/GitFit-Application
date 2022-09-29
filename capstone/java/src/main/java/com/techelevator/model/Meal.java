package com.techelevator.model;

import java.time.LocalDate;

public class Meal {

    private int mealId;
    private int profileId;
    private String mealType;
    private LocalDate mealDate;

    public Meal(int mealId, int profileId, String mealType, LocalDate mealDate) {
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

    public int getProfileId() {
        return profileId;
    }

    public void setProfileId(int profileId) {
        this.profileId = profileId;
    }

    public void setMealDate(LocalDate mealDate) {
        this.mealDate = mealDate;
    }
}
