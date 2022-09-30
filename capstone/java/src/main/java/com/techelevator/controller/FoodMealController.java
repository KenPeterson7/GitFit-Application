package com.techelevator.controller;

import com.techelevator.dao.FoodMealDao;
import com.techelevator.model.FoodMeal;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin
@RestController
@PreAuthorize("isAuthenticated()")
@RequestMapping(path = "/foodmeal")
public class FoodMealController {

    private FoodMealDao foodMealDao;

    public FoodMealController(FoodMealDao foodMealDao) {
        this.foodMealDao = foodMealDao;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(path = "", method = RequestMethod.POST)
    public boolean addFoodMeal(@Valid @RequestBody FoodMeal foodmeal){
        return foodMealDao.addFoodMeal(foodmeal.getMealId(), foodmeal.getFoodId());
    }

    @RequestMapping(path = "/{mid}/{fid}", method = RequestMethod.DELETE)
    public boolean deleteFoodMeal(@PathVariable("mid") int meal_id, @PathVariable("fid") int food_id){
        return foodMealDao.deleteFoodMeal(meal_id, food_id);
    }
}
