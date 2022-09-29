package com.techelevator.dao;

import com.techelevator.model.Goal;

public interface GoalDao {

    Goal createGoal(Goal goal);

    boolean updateGoal(Goal goal, int profile_id);

    Goal findGoalByProfileId(int profile_id);

    boolean deleteGoal(int profile_id);

}
