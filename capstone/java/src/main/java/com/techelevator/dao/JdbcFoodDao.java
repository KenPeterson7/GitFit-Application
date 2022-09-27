package com.techelevator.dao;

import com.techelevator.model.Food;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

public class JdbcFoodDao implements FoodDao{

    private JdbcTemplate jdbcTemplate;

    public JdbcFoodDao(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public boolean addFood(Food food) {
        String sql = "INSERT INTO food (food_name, food_type, size, " +
                "number_servings, meal, caloric_amount) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?) " +
                "RETURNING food_id ;";

        Integer newId = jdbcTemplate.queryForObject(sql, Integer.class,
                food.getFoodName(),
                food.getFoodType(),
                food.getSize(),
                food.getNumberOfServings(),
                food.getMeal(),
                food.getCaloricAmount());

        return false;
    }

    @Override
    public boolean modifyFood(Food food) {
        return false;
    }

    private Food mapRowToFood(SqlRowSet rs) {
        Food food = new Food();
        food.setFoodId(rs.getInt("food_id"));
        food.setFoodName(rs.getString("food_name"));
        food.setFoodType(rs.getString("food_type"));
        food.setSize(rs.getDouble("size"));
        food.setNumberOfServings(rs.getInt("number_servings"));
        food.setMeal(rs.getString("meal"));
        food.setCaloricAmount(rs.getInt("caloric_amount"));
        return food;
    }

}
