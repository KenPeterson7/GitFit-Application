package com.techelevator.model;

import java.time.LocalDate;

public class Workout {

    private int workoutId;
    private String nameOfWorkout;
    private String typeOfWorkout;
    private int duration;
    private LocalDate workoutDate;
    private int profileId;
    private double caloriesBurned;

    public Workout(int workoutId, String nameOfWorkout, String typeOfWorkout, int duration, LocalDate workoutDate, int profileId, int caloriesBurned) {
        this.workoutId = workoutId;
        this.nameOfWorkout = nameOfWorkout;
        this.typeOfWorkout = typeOfWorkout;
        this.duration = duration;
        this.workoutDate = workoutDate;
        this.profileId = profileId;
        this.caloriesBurned = caloriesBurned;
    }

    public Workout() {

    }

    public int getWorkoutId() {
        return workoutId;
    }

    public void setWorkoutId(int workoutId) {
        this.workoutId = workoutId;
    }

    public String getNameOfWorkout() {
        return nameOfWorkout;
    }

    public void setNameOfWorkout(String nameOfWorkout) {
        this.nameOfWorkout = nameOfWorkout;
    }

    public String getTypeOfWorkout() {
        return typeOfWorkout;
    }

    public void setTypeOfWorkout(String typeOfWorkout) {
        this.typeOfWorkout = typeOfWorkout;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public int getProfileId() {
        return profileId;
    }

    public void setProfileId(int profileId) {
        this.profileId = profileId;
    }

    public LocalDate getWorkoutDate() {
        return workoutDate;
    }

    public void setWorkoutDate(LocalDate workoutDate) {
        this.workoutDate = workoutDate;
    }

    public double getCaloriesBurned() {
        return caloriesBurned;
    }

    public void setCaloriesBurned(double caloriesBurned) {
        this.caloriesBurned = caloriesBurned;
    }
}
