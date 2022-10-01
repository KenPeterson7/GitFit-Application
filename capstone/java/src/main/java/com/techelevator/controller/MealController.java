package com.techelevator.controller;


import com.techelevator.dao.MealDao;
import com.techelevator.model.Meal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.List;

@RestController
@CrossOrigin
public class MealController {

    @Autowired
    private MealDao mealDao;

    public MealController(MealDao mealDao) {

        this.mealDao = mealDao;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(path = "/meal", method = RequestMethod.POST)
    public Meal addMeal(@Valid @RequestBody Meal meal) {

        return mealDao.addMeal(meal);
    }

    @RequestMapping(path = "/meal/{id}", method = RequestMethod.PUT)
    public boolean modifyMeal(@PathVariable("id") int mealId, @Valid @RequestBody Meal modifiedMeal) {
        return mealDao.modifyMeal(mealId, modifiedMeal);
    }

    @RequestMapping(path = "/meal", method = RequestMethod.GET)
    public List<Meal> getListOfAllMeal() {
        return mealDao.listOfAllMeals();
    }


    @RequestMapping(path = "/meal/{mealType}/{mealDate}/{pid}", method = RequestMethod.GET)
    public int getMealIdByMeal(@PathVariable("mealType") String mealType,
                               @PathVariable("mealDate") String mealDate,
                               @PathVariable("pid") int profileId) {

        LocalDate date = LocalDate.parse(mealDate);

        return mealDao.findMealIdByMeal(mealType,date, profileId);
    }

}
