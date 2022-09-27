package com.techelevator.dao;

import com.techelevator.model.Food;

import java.util.List;

public interface FoodDao {

    Food addFood(Food food);

    boolean modifyFood(int foodId, Food modifiedFood);

    List<Food> listOfAllFoods();

}
