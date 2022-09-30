package com.techelevator.dao;

import com.techelevator.model.Meal;

import java.time.LocalDate;
import java.util.List;

public interface MealDao {

    Meal addMeal(Meal meal);

    boolean modifyMeal(int mealId, Meal modifiedMeal);

    List<Meal> listOfAllMeals();


    int findMealIdByMeal(String mealType, LocalDate mealDate, int profileId);


}
