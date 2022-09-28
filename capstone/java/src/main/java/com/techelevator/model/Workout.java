package com.techelevator.model;

public class Workout {

    private int workoutId;
    private String nameOfWorkout;
    private String typeOfWorkout;
    private int duration;

    public Workout(int workoutId, String nameOfWorkout, String typeOfWorkout, int duration) {
        this.workoutId = workoutId;
        this.nameOfWorkout = nameOfWorkout;
        this.typeOfWorkout = typeOfWorkout;
        this.duration = duration;
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
}
