package com.techelevator.dao;

import com.techelevator.model.FoodMeal;

public interface FoodMealDao {

    boolean addFoodMeal(int mealId, int foodId);

    boolean deleteFoodMeal(int mealId, int foodId);

}
