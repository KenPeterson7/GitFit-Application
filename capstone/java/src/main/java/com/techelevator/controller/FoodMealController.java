package com.techelevator.controller;

import com.techelevator.dao.FoodMealDao;
import com.techelevator.model.FoodMeal;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin
@RestController
public class FoodMealController {

    private FoodMealDao foodMealDao;

    public FoodMealController(FoodMealDao foodMealDao) {
        this.foodMealDao = foodMealDao;
    }

    @RequestMapping(path = "/foodmeal", method = RequestMethod.POST)
    public boolean addFoodMeal(@Valid @RequestBody FoodMeal foodmeal){
        return foodMealDao.addFoodMeal(foodmeal.getMealId(), foodmeal.getFoodId());
    }
}
