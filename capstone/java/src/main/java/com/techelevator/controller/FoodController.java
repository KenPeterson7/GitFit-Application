package com.techelevator.controller;


import com.techelevator.dao.FoodDao;
import com.techelevator.model.Food;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.List;

@RestController
@CrossOrigin
public class FoodController {

    @Autowired
    private FoodDao foodDao;

    public FoodController(FoodDao foodDao) {

        this.foodDao = foodDao;
    }

    @RequestMapping(path = "/food", method = RequestMethod.POST)
    public Food addFood(@Valid @RequestBody Food food) {

        return foodDao.addFood(food);
    }

    @RequestMapping(path = "/food/{id}", method = RequestMethod.PUT)
    public boolean modifyFood(@PathVariable("id") int foodId, @Valid @RequestBody Food modifiedFood) {
        return foodDao.modifyFood(foodId, modifiedFood);
    }

    @RequestMapping(path = "/food/user/{username}", method = RequestMethod.GET)
    public List<Food> getListOfAllFoodsByUsername(@PathVariable("username") String username) {

        return foodDao.listOfAllFoodsByUsername(username);
    }

    @RequestMapping(path = "/food/date/{date}", method = RequestMethod.GET)
    public List<Food> getListOfAllFoodsByDate(@PathVariable("date")LocalDate mealDate) {

        return foodDao.listOfAllFoodsByDate(mealDate);
    }

    @RequestMapping(path = "/food/{id}", method = RequestMethod.DELETE)
    public void deleteFood(@PathVariable("id") int foodId) {
        foodDao.deleteFood(foodId);
    }

//    @RequestMapping(path = "/food/breakfast", method = RequestMethod.GET)
//    public List<Food> getListOfAllBreakfastFoods() {
//
//        return foodDao.listOfAllBreakfastFoods();
//    }
//
//    @RequestMapping(path = "/food/lunch", method = RequestMethod.GET)
//    public List<Food> getListOfAlLunchFoods() {
//
//        return foodDao.listOfAllLunchFoods();
//    }
//
//    @RequestMapping(path = "/food/dinner", method = RequestMethod.GET)
//    public List<Food> getListOfAllDinnerFoods() {
//
//        return foodDao.listOfAllDinnerFoods();
//    }
//
//    @RequestMapping(path = "/food/snack", method = RequestMethod.GET)
//    public List<Food> getListOfAllSnackFood() {
//
//        return foodDao.listOfAllSnackFoods();
//    }


}
