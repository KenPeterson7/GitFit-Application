package com.techelevator.dao;

import com.techelevator.model.Food;

public interface FoodDao {

    boolean addFood(Food food);

    boolean modifyFood(Food food);

}
