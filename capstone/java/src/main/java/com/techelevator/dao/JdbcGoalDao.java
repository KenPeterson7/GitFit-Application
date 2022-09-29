package com.techelevator.dao;

import com.techelevator.model.Goal;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

import java.nio.file.ProviderNotFoundException;

@Repository
public class JdbcGoalDao implements GoalDao {

    private JdbcTemplate jdbcTemplate;

    public JdbcGoalDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Goal createGoal(Goal goal) {
        String sql = "insert into goal(profile_id, daily_caloric_goal) values(?, ?) returning goal_id";
        Integer newGoalId = jdbcTemplate.queryForObject(sql, Integer.class, goal.getProfile_id(), goal.getDaily_caloric_goal());
        goal.setGoal_id(newGoalId);
        return goal;
    }

    @Override
    public boolean updateGoal(Goal goal, int profile_id) {
        String sql = "Update goal set daily_caloric_goal = ? where profile_id = ?";
        return jdbcTemplate.update(sql, goal.getDaily_caloric_goal(), profile_id) == 1;
    }

    @Override
    public Goal findGoalByProfileId(int profile_id) {
        String sql = "select * from goal where profile_id = ?";
        SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sql, profile_id);
        if(rowSet.next()){
            return mapRowToGoal(rowSet);
        }
        throw new ProviderNotFoundException("Goal " + profile_id + " was not found.");
    }

    @Override
    public boolean deleteGoal(int profile_id) {
        String sql = "delete from goal where profile_id = ?";
        return jdbcTemplate.update(sql, profile_id) == 1;
    }

    private Goal mapRowToGoal(SqlRowSet rs){
        Goal goal = new Goal();
        goal.setGoal_id(rs.getInt("goal_id"));
        goal.setProfile_id(rs.getInt("profile_id"));
        goal.setDaily_caloric_goal(rs.getInt("daily_caloric_goal"));
        return goal;
    }
}
