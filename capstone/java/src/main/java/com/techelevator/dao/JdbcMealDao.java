package com.techelevator.dao;

import com.techelevator.model.Meal;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Repository
public class JdbcMealDao implements MealDao{

    private JdbcTemplate jdbcTemplate;

    public JdbcMealDao(JdbcTemplate jdbcTemplate){

        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Meal addMeal(Meal meal) {
        String sql =
                "INSERT INTO meal (meal_type, meal_date) " +
                        "VALUES(?, ?) " +
                        "RETURNING meal_id ;";

        Integer newId = jdbcTemplate.queryForObject(sql, Integer.class,
            meal.getMealType(),
            meal.getMealDate());

        meal.setMealId(newId);

        return meal;
    }

    @Override
    public boolean modifyMeal(int mealId, Meal modifiedMeal) {

        String sql =
                "UPDATE meal " +
                        "SET meal_type = ?, meal_date = ?" +
                        "WHERE meal_id = ? ";

        return jdbcTemplate.update(sql,
                modifiedMeal.getMealType(), modifiedMeal.getMealDate(), mealId) == 1;
    }

    @Override
    public List<Meal> listOfAllMeals() {

        List<Meal> mealList = new ArrayList<>();
        String sql =
                "SELECT * " +
                        "FROM meal ;";

        SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
        while( results.next() ) {
            Meal meal = mapRowToMeal(results);
            mealList.add(meal);
        }
        return mealList;
    }

    private Meal mapRowToMeal(SqlRowSet rs) {
        Meal meal = new Meal();
        meal.setMealId(rs.getInt("meal_id"));
        meal.setMealType(rs.getString("meal_type"));
        meal.setMealDate(Objects.requireNonNull(rs.getDate("meal_date")).toLocalDate());
        return meal;
    }


}
