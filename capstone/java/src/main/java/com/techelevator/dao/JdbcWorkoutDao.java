package com.techelevator.dao;

import com.techelevator.model.Workout;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Repository
public class JdbcWorkoutDao implements WorkoutDao{

    private JdbcTemplate jdbcTemplate;

    public JdbcWorkoutDao(JdbcTemplate jdbcTemplate) {

        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Workout addWorkout(Workout workout) {

        String sql =
                "INSERT INTO workout (profile_id, name_of_workout, type_of_workout, duration, workout_date) " +
                        "VALUES(?, ?, ?, ? , ?) " +
                        "RETURNING workout_id ;";

        Integer newId = jdbcTemplate.queryForObject(sql, Integer.class,
                workout.getProfileId(),
                workout.getNameOfWorkout(),
                workout.getTypeOfWorkout(),
                workout.getDuration(),
                workout.getWorkoutDate());

        workout.setWorkoutId(newId);

        return workout;
    }

    @Override
    public boolean modifyWorkout(int workoutId, Workout modifiedWorkout) {

        String sql =
                "UPDATE workout " +
                        "SET profile_id = ?, name_of_workout = ?, type_of_workout = ?," +
                        "duration = ?, workout_date = ? " +
                        "WHERE workout_id = ? ;";

        return jdbcTemplate.update(sql,
                modifiedWorkout.getProfileId(),
                modifiedWorkout.getNameOfWorkout(),
                modifiedWorkout.getTypeOfWorkout(),
                modifiedWorkout.getDuration(),
                modifiedWorkout.getWorkoutDate(),
                workoutId) == 1;
    }

    @Override
    public List<Workout> listOfAllWorkoutsByUsername(String username) {

        List<Workout> workoutList = new ArrayList<>();
        String sql =
                "SELECT * " +
                        "FROM workout " +
                        "JOIN profile ON profile.profile_id = workout.profile_id " +
                        "WHERE profile.username = ? ;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, username);
        while( results.next() ) {
            Workout workout = mapRowToWorkout(results);
            workoutList.add(workout);
        }
        return workoutList;
    }

    @Override
    public List<Workout> listOfAllWorkoutsByDate(LocalDate workoutDate) {
        List<Workout> workoutList = new ArrayList<>();
        String sql =
                "SELECT * " +
                        "FROM workout " +
                        "WHERE workout_date = ? ;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, workoutDate);
        while( results.next() ) {
            Workout workout = mapRowToWorkout(results);
            workoutList.add(workout);
        }
        return workoutList;
    }

    @Override
    public boolean deleteWorkout(int workoutId) {

        String sql = "DELETE from workout " +
                "WHERE workout_id = ? ;";

        return jdbcTemplate.update(sql, workoutId) == 1;
    }

    @Override
    public Workout getWorkoutByWorkoutId(int workoutId) {
        Workout workout = null;
        String sql =
                "SELECT * " +
                        "FROM workout " +
                        "WHERE workout_id = ? ;";

        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, workoutId);
        if ( results.next() ) {
            workout = mapRowToWorkout(results);
        }
        return workout;
    }


    private Workout mapRowToWorkout(SqlRowSet rs ){
        Workout workout = new Workout();
        workout.setWorkoutId(rs.getInt("workout_id"));
        workout.setNameOfWorkout(rs.getString("name_of_workout"));
        workout.setTypeOfWorkout(rs.getString("type_of_workout"));
        workout.setDuration(rs.getInt("duration"));
        workout.setProfileId(rs.getInt("profile_id"));
        workout.setWorkoutDate(Objects.requireNonNull(rs.getDate("workout_date")).toLocalDate());
        return workout;
    }

}
