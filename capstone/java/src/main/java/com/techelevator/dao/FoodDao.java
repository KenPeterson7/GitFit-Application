package com.techelevator.dao;

import com.techelevator.model.Food;
import org.apache.tomcat.jni.Local;

import java.time.LocalDate;
import java.util.List;

public interface FoodDao {

    Food addFood(Food food);

    boolean modifyFood(int foodId, Food modifiedFood);

    List<Food> listOfAllFoodsByUsername(String username);

    List<Food> listOfAllFoodsByDate(LocalDate mealDate);

    boolean deleteFood(int foodId);

    List<Food> listOfFoodsByUserNameDateMealType(String username, LocalDate date, String mealType);

    int findFoodIdByFood(String foodName);

    List<Food> listOfLastThreeMeals(String username, String mealType);

    List<Food> getLastMeal(String username, String mealType);

    int totalCaloriesPerDay(String username, LocalDate date);


//
//    List<Food> listOfAllLunchFoods();
//
//    List<Food> listOfAllDinnerFoods();
//
//    List<Food> listOfAllSnackFoods();

}
