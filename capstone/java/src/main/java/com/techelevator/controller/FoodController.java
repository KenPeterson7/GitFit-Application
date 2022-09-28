package com.techelevator.controller;


import com.techelevator.dao.FoodDao;
import com.techelevator.model.Food;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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

    @RequestMapping(path = "/food", method = RequestMethod.GET)
    public List<Food> getListOfAllFood() {
        return foodDao.listOfAllFoods();
    }

}
