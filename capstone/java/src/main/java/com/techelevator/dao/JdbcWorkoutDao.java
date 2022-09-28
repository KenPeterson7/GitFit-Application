package com.techelevator.dao;

import com.techelevator.model.Workout;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class JdbcWorkoutDao implements WorkoutDao{

    private JdbcTemplate jdbcTemplate;

    public JdbcWorkoutDao(JdbcTemplate jdbcTemplate) {

        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Workout addWorkout(Workout workout) {
        return null;
    }

    @Override
    public boolean modifyWorkout(int workoutId, Workout modifiedWorkout) {
        return false;
    }

    @Override
    public List<Workout> listOfAllWorkouts() {
        return null;
    }

//    private Workout mapRowToWorkout(SqlRowSet rs ){
//        Workout workout = new Workout();
//        workout.setWorkoutId(rs.getInt("workout_id"));
//        workout.getString("workout_name");
//
//    }

}
