package com.techelevator.dao;

import com.techelevator.model.Workout;

import java.time.LocalDate;
import java.util.List;

public interface WorkoutDao {

    Workout addWorkout(Workout workout);

    boolean modifyWorkout(int workoutId, Workout modifiedWorkout);

    List<Workout> listOfAllWorkoutsByUsername(String username);

    List<Workout> listOfAllWorkoutsByDate(LocalDate workoutDate);

    boolean deleteWorkout(int workoutId);

    // get one workout by workout_id
    Workout getWorkoutByWorkoutId(int workoutId);

    List<Workout> getAllWorkouts();

    List<Workout> listOfLastThreeWorkouts(String username);

}
