package com.techelevator.dao;

import com.techelevator.model.Workout;

import java.util.List;

public interface WorkoutDao {

    Workout addWorkout(Workout workout);

    boolean modifyWorkout(int workoutId, Workout modifiedWorkout);

    List<Workout> listOfAllWorkouts();

}
