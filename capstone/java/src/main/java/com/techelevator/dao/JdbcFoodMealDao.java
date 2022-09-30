package com.techelevator.dao;

import com.techelevator.model.FoodMeal;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

@Repository
public class JdbcFoodMealDao implements FoodMealDao{

    private JdbcTemplate jdbcTemplate;

    public JdbcFoodMealDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public boolean addFoodMeal(int mealId, int foodId) {
        String sql = "insert into food_meal(meal_id, food_id) values(?, ?)";
        return jdbcTemplate.update(sql, mealId, foodId) == 1;
    }

    @Override
    public boolean deleteFoodMeal(int mealId, int foodId) {
        String sql ="delete from food_meal where meal_id = ? and food_id = ?";
        return jdbcTemplate.update(sql, mealId, foodId) == 1;
    }

    private FoodMeal mapRowToFoodMeal(SqlRowSet rs){
        FoodMeal foodMeal = new FoodMeal();
        foodMeal.setMealId(rs.getInt("meal_id"));
        foodMeal.setFoodId(rs.getInt("food_id"));
        return foodMeal;
    }
}
