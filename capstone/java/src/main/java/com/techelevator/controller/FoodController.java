package com.techelevator.controller;


import com.techelevator.dao.FoodDao;
import com.techelevator.model.Food;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

    @ResponseStatus(HttpStatus.CREATED)
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
    public List<Food> getListOfAllFoodsByDate(@PathVariable("date")String mealDate) {

        LocalDate date = LocalDate.parse(mealDate);

        return foodDao.listOfAllFoodsByDate(date);
    }
@RequestMapping(path = "/food/{username}/{md}/{mt}", method = RequestMethod.GET)
    public List<Food> listOfFoodsByUsernameDateMealType(@PathVariable("username") String username,
                                                        @PathVariable("md") String date,
                                                        @PathVariable("mt") String mealType){
    LocalDate newDate = LocalDate.parse(date);

    return foodDao.listOfFoodsByUserNameDateMealType(username, newDate, mealType);
    }
    @RequestMapping(path = "/food/{id}", method = RequestMethod.DELETE)
    public void deleteFood(@PathVariable("id") int foodId) {
        foodDao.deleteFood(foodId);
    }

    @RequestMapping(path = "/food/{foodName}", method = RequestMethod.GET)
    public int getFoodIdByFoodName(@PathVariable("foodName") String foodName) {
        return foodDao.findFoodIdByFood(foodName);
    }

    @RequestMapping(path = "/food/user/recent/{username}/{mt}", method = RequestMethod.GET)
    public List<Food> getListOfLastThreeMeals(@PathVariable("username") String username,
                                             @PathVariable("mt") String mealType) {

        return foodDao.listOfLastThreeMeals(username, mealType);
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
