package com.techelevator.dao;

import com.techelevator.model.Food;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
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
                "number_servings, caloric_amount) " +
                "VALUES (?, ?, ?, ?, ?) " +
                "RETURNING food_id ;";

        Integer newId = jdbcTemplate.queryForObject(sql, Integer.class,
                food.getFoodName(),
                food.getFoodType(),
                food.getSize(),
                food.getNumberOfServings(),
                food.getCaloricAmount());

        food.setFoodId(newId);

        return food;
    }

    @Override
    public boolean modifyFood(int foodId, Food modifiedFood) {

        String sql =
                "UPDATE food " +
                        "SET food_name = ?, food_type = ?, size = ?, " +
                        "number_servings = ?, caloric_amount = ? " +
                        "WHERE food_id = ? ";

        return jdbcTemplate.update(sql, modifiedFood.getFoodName(),
                modifiedFood.getFoodType(), modifiedFood.getSize(),
                modifiedFood.getNumberOfServings(),
                modifiedFood.getCaloricAmount(), foodId) == 1;
    }

    @Override
    public List<Food> listOfAllFoodsByUsername(String username) {

        List<Food> foodListByUsername = new ArrayList<Food>();
        String sql =
                "SELECT * " +
                        "FROM food " +
                        "JOIN food_meal ON food_meal.food_id = food.food_id " +
                        "JOIN meal ON meal.meal_id = food_meal.meal_id " +
                        "JOIN profile ON profile.profile_id = meal.profile_id " +
                        "WHERE profile.username = ? ;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, username);
        while( results.next() ) {
            Food food = mapRowToFood(results);
            foodListByUsername.add(food);
        }
        return foodListByUsername;
    }

    @Override
    public List<Food> listOfAllFoodsByDate(LocalDate mealDate) {

        List<Food> foodListByDate = new ArrayList<Food>();
        String sql =
                "SELECT * " +
                        "FROM food " +
                        "JOIN food_meal ON food_meal.food_id = food.food_id " +
                        "JOIN meal ON meal.meal_id = food_meal.meal_id " +
                        "JOIN profile ON profile.profile_id = meal.profile_id " +
                        "WHERE meal.meal_date = ? ;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, mealDate);
        while( results.next() ) {
            Food food = mapRowToFood(results);
            foodListByDate.add(food);
        }
        return foodListByDate;
    }

    @Override
    public boolean deleteFood(int foodId) {

        String sql = "DELETE from food WHERE food_id = ? ";

        return jdbcTemplate.update(sql, foodId) == 1;
    }

    @Override
    public List<Food> listOfFoodsByUserNameDateMealType(String username, LocalDate date, String mealType) {
        List<Food> newFoodList = new ArrayList<Food>();
        String sql = "select * from food\n" +
                "join food_meal on food_meal.food_id = food.food_id\n" +
                "join meal on meal.meal_id = food_meal.meal_id\n" +
                "join profile on profile.profile_id = meal.profile_id\n" +
                "where profile.username = ? and meal_date = ? and meal_type = ?";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, username, date, mealType);
        while( results.next() ) {
            Food food = mapRowToFood(results);
            newFoodList.add(food);
        }
        return newFoodList;
    }

//    @Override
//    public List<Food> listOfAllBreakfastFoods() {
//
//        List<Food> breakfastList = new ArrayList<Food>();
//        String sql =
//                "SELECT * " +
//        "FROM food " +
//        "JOIN food_meal ON food_meal.food_id = food.food_id " +
//        "JOIN meal ON meal.meal_id = food_meal.meal_id " +
//                        "JOIN profile ON profile.profile_id = meal.profile_id " +
//        "WHERE meal.meal_date = ? AND  food.meal_type = 'breakfast' AND profile.username = ? ;";
//
//        SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
//        while( results.next() ) {
//            Food food = mapRowToFood(results);
//            breakfastList.add(food);
//        }
//        return breakfastList;
//    }
//
//    @Override
//    public List<Food> listOfAllLunchFoods() {
//
//        List<Food> lunchList = new ArrayList<Food>();
//        String sql =
//                "SELECT * " +
//                        "FROM food " +
//                        "JOIN food_meal ON food_meal.food_id = food.food_id " +
//                        "JOIN meal ON meal.meal_id = food_meal.meal_id " +
//                        "WHERE meal.meal_date = ? AND  food.meal_type = 'lunch' ;";
//
//        SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
//        while( results.next() ) {
//            Food food = mapRowToFood(results);
//            lunchList.add(food);
//        }
//        return lunchList;
//    }
//
//    @Override
//    public List<Food> listOfAllDinnerFoods() {
//        List<Food> dinnerList = new ArrayList<Food>();
//        String sql =
//                "SELECT * " +
//                        "FROM food " +
//                        "JOIN food_meal ON food_meal.food_id = food.food_id " +
//                        "JOIN meal ON meal.meal_id = food_meal.meal_id " +
//                        "WHERE meal.meal_date = ? AND  food.meal_type = 'dinner' ;";
//
//        SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
//        while( results.next() ) {
//            Food food = mapRowToFood(results);
//            dinnerList.add(food);
//        }
//        return dinnerList;
//    }
//
//    @Override
//    public List<Food> listOfAllSnackFoods() {
//        List<Food> snackList = new ArrayList<Food>();
//        String sql =
//                "SELECT * " +
//                        "FROM food " +
//                        "JOIN food_meal ON food_meal.food_id = food.food_id " +
//                        "JOIN meal ON meal.meal_id = food_meal.meal_id " +
//                        "WHERE meal.meal_date = ? AND  food.meal_type = 'snack' ;";
//
//        SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
//        while( results.next() ) {
//            Food food = mapRowToFood(results);
//            snackList.add(food);
//        }
//        return snackList;
//    }


    private Food mapRowToFood(SqlRowSet rs) {
        Food food = new Food();
        food.setFoodId(rs.getInt("food_id"));
        food.setFoodName(rs.getString("food_name"));
        food.setFoodType(rs.getString("food_type"));
        food.setSize(rs.getString("size"));
        food.setNumberOfServings(rs.getInt("number_servings"));
        food.setCaloricAmount(rs.getInt("caloric_amount"));
        return food;
    }

}
