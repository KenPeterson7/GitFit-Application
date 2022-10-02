package com.techelevator.controller;

import com.techelevator.dao.WorkoutDao;
import com.techelevator.model.Food;
import com.techelevator.model.Workout;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.List;

@RestController
@CrossOrigin
public class WorkoutController {

    @Autowired
    private WorkoutDao workoutDao;

    public WorkoutController(WorkoutDao workoutDao) {
        this.workoutDao = workoutDao;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(path = "/workout", method = RequestMethod.POST)
    public Workout addWorkout(@Valid @RequestBody Workout workout) {

        return workoutDao.addWorkout(workout);
    }

    @RequestMapping(path = "/workout/{id}", method = RequestMethod.PUT)
    public boolean modifyWorkout(@PathVariable("id") int workoutId, @Valid @RequestBody Workout modifiedWorkout) {
        return workoutDao.modifyWorkout(workoutId, modifiedWorkout);
    }

    @RequestMapping(path = "/workout/user/{username}", method = RequestMethod.GET)
    public List<Workout> getListOfAllWorkoutsByUsername(@PathVariable("username") String username) {

        return workoutDao.listOfAllWorkoutsByUsername(username);
    }

    @RequestMapping(path = "/workout/date/{date}", method = RequestMethod.GET)
    public List<Workout> getListOfAllFoodsByDate(@PathVariable("date")String workoutDate) {

        LocalDate date = LocalDate.parse(workoutDate);

        return workoutDao.listOfAllWorkoutsByDate(date);
    }

    @RequestMapping(path = "/workout/{id}", method = RequestMethod.DELETE)
    public void deleteWorkout(@PathVariable("id") int workoutId) {

        workoutDao.deleteWorkout(workoutId);
    }

    @RequestMapping(path = "/workout/{id}", method = RequestMethod.GET)
    public Workout getWorkout(@PathVariable("id") int workoutId) {

        return workoutDao.getWorkoutByWorkoutId(workoutId);
    }

}
