package com.techelevator.dao;

import com.techelevator.model.Food;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

@Repository
public class JdbcFoodDao implements FoodDao{

    private JdbcTemplate jdbcTemplate;

    public JdbcFoodDao(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Food addFood(Food food) {
        String sql =
                "INSERT INTO food (food_name, type, size, " +
                "number_servings, meal, caloric_amount) " +
                "VALUES (?, ?, ?, ?, ?, ?) " +
                "RETURNING food_id ;";

        Integer newId = jdbcTemplate.queryForObject(sql, Integer.class,
                food.getFoodName(),
                food.getFoodType(),
                food.getSize(),
                food.getNumberOfServings(),
                food.getMeal(),
                food.getCaloricAmount());

        food.setFoodId(newId);

        return food;
    }

    @Override
    public boolean modifyFood(int foodId, Food modifiedFood) {

        String sql =
                "UPDATE food " +
                        "SET food_name = ?, type = ?, size = ?, " +
                        "number_servings = ?, meal = ?, caloric_amount = ? " +
                        "WHERE food_id = ? ";

        return jdbcTemplate.update(sql, modifiedFood.getFoodName(),
                modifiedFood.getFoodType(), modifiedFood.getSize(),
                modifiedFood.getNumberOfServings(), modifiedFood.getMeal(),
                modifiedFood.getCaloricAmount(), foodId) == 1;
    }


    private Food mapRowToFood(SqlRowSet rs) {
        Food food = new Food();
        food.setFoodId(rs.getInt("food_id"));
        food.setFoodName(rs.getString("food_name"));
        food.setFoodType(rs.getString("type"));
        food.setSize(rs.getDouble("size"));
        food.setNumberOfServings(rs.getInt("number_servings"));
        food.setMeal(rs.getString("meal"));
        food.setCaloricAmount(rs.getInt("caloric_amount"));
        return food;
    }

}
