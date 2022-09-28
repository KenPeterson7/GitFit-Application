package com.techelevator.model;

public class Food {

    private int foodId;
    private String foodName;
    private String foodType;
    private double size;
    private int numberOfServings;
    private String mealType;
    private int caloricAmount;

    public Food(int foodId, String foodName, String foodType, double size, int numberOfServings, String mealType, int caloricAmount) {
        this.foodId = foodId;
        this.foodName = foodName;
        this.foodType = foodType;
        this.size = size;
        this.numberOfServings = numberOfServings;
        this.mealType = mealType;
        this.caloricAmount = caloricAmount;
    }

    public Food() {

    }

    public int getFoodId() {
        return foodId;
    }

    public void setFoodId(int foodId) {
        this.foodId = foodId;
    }

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public String getFoodType() {
        return foodType;
    }

    public void setFoodType(String foodType) {
        this.foodType = foodType;
    }

    public double getSize() {
        return size;
    }

    public void setSize(double size) {
        this.size = size;
    }

    public int getNumberOfServings() {
        return numberOfServings;
    }

    public void setNumberOfServings(int numberOfServings) {
        this.numberOfServings = numberOfServings;
    }

    public String getMealType() {
        return mealType;
    }

    public void setMealType(String mealType) {
        this.mealType = mealType;
    }

    public int getCaloricAmount() {
        return caloricAmount;
    }

    public void setCaloricAmount(int caloricAmount) {
        this.caloricAmount = caloricAmount;
    }
}
