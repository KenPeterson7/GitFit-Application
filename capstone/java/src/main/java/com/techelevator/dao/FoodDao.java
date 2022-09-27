package com.techelevator.dao;

import com.techelevator.model.Food;

public interface FoodDao {

    Food addFood(Food food);

    boolean modifyFood(int foodId, Food modifiedFood);

}
