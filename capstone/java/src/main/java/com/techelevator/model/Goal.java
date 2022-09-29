package com.techelevator.model;

public class Goal {

    private int goal_id;

    private int profile_id;

    private int daily_caloric_goal;

    public Goal(int goal_id, int profile_id, int daily_caloric_goal) {
        this.goal_id = goal_id;
        this.profile_id = profile_id;
        this.daily_caloric_goal = daily_caloric_goal;
    }

    public Goal() {}

    public int getGoal_id() {
        return goal_id;
    }

    public void setGoal_id(int goal_id) {
        this.goal_id = goal_id;
    }

    public int getProfile_id() {
        return profile_id;
    }

    public void setProfile_id(int profile_id) {
        this.profile_id = profile_id;
    }

    public int getDaily_caloric_goal() {
        return daily_caloric_goal;
    }

    public void setDaily_caloric_goal(int daily_caloric_goal) {
        this.daily_caloric_goal = daily_caloric_goal;
    }
}
