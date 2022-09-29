package com.techelevator.model;

public class Food {

    private int foodId;
    private String foodName;
    private String foodType;
    private String size;
    private int numberOfServings;
    private int caloricAmount;

    public Food(int foodId, String foodName, String foodType, String size, int numberOfServings, int caloricAmount) {
        this.foodId = foodId;
        this.foodName = foodName;
        this.foodType = foodType;
        this.size = size;
        this.numberOfServings = numberOfServings;
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

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public int getNumberOfServings() {
        return numberOfServings;
    }

    public void setNumberOfServings(int numberOfServings) {
        this.numberOfServings = numberOfServings;
    }


    public int getCaloricAmount() {
        return caloricAmount;
    }

    public void setCaloricAmount(int caloricAmount) {
        this.caloricAmount = caloricAmount;
    }
}
