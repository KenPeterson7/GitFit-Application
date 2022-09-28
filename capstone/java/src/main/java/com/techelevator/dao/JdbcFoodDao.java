package com.techelevator.dao;

import com.techelevator.model.Food;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class JdbcFoodDao implements FoodDao{

    private JdbcTemplate jdbcTemplate;

    public JdbcFoodDao(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Food addFood(Food food) {
        String sql =
                "INSERT INTO food (food_name, food_type, size, " +
                "number_servings, meal_type, caloric_amount) " +
                "VALUES (?, ?, ?, ?, ?, ?) " +
                "RETURNING food_id ;";

        Integer newId = jdbcTemplate.queryForObject(sql, Integer.class,
                food.getFoodName(),
                food.getFoodType(),
                food.getSize(),
                food.getNumberOfServings(),
                food.getMealType(),
                food.getCaloricAmount());

        food.setFoodId(newId);

        return food;
    }

    @Override
    public boolean modifyFood(int foodId, Food modifiedFood) {

        String sql =
                "UPDATE food " +
                        "SET food_name = ?, food_type = ?, size = ?, " +
                        "number_servings = ?, meal_type = ?, caloric_amount = ? " +
                        "WHERE food_id = ? ";

        return jdbcTemplate.update(sql, modifiedFood.getFoodName(),
                modifiedFood.getFoodType(), modifiedFood.getSize(),
                modifiedFood.getNumberOfServings(), modifiedFood.getMealType(),
                modifiedFood.getCaloricAmount(), foodId) == 1;
    }

    @Override
    public List<Food> listOfAllFoods() {

        List<Food> foodList = new ArrayList<Food>();
        String sql =
                "SELECT food_id, food_name, food_type, size, " +
                        "number_servings, meal_type, caloric_amount " +
                        "FROM food ;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
        while( results.next() ) {
            Food food = mapRowToFood(results);
            foodList.add(food);
        }
        return foodList;
    }


    private Food mapRowToFood(SqlRowSet rs) {
        Food food = new Food();
        food.setFoodId(rs.getInt("food_id"));
        food.setFoodName(rs.getString("food_name"));
        food.setFoodType(rs.getString("food_type"));
        food.setSize(rs.getDouble("size"));
        food.setNumberOfServings(rs.getInt("number_servings"));
        food.setMealType(rs.getString("meal_type"));
        food.setCaloricAmount(rs.getInt("caloric_amount"));
        return food;
    }

}
