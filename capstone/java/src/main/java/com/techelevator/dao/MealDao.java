package com.techelevator.dao;

import com.techelevator.model.Meal;

import java.util.List;

public interface MealDao {

    Meal addMeal(Meal meal);

    boolean modifyMeal(int mealId, Meal modifiedMeal);

    List<Meal> listOfAllMeals();



}
