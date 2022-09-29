package com.techelevator.model;


public class FoodMeal {

    private int mealId;

    private int foodId;

    public FoodMeal(int mealId, int foodId) {
        this.mealId = mealId;
        this.foodId = foodId;
    }

    public FoodMeal() {}

    public int getMealId() {
        return mealId;
    }

    public void setMealId(int mealId) {
        this.mealId = mealId;
    }

    public int getFoodId() {
        return foodId;
    }

    public void setFoodId(int foodId) {
        this.foodId = foodId;
    }
}
