package com.techelevator.controller;

import com.techelevator.dao.GoalDao;
import com.techelevator.model.Goal;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin
@RestController
public class GoalController {

    private GoalDao goalDao;

    public GoalController(GoalDao goalDao) {
        this.goalDao = goalDao;
    }

    @RequestMapping(path = "/profile/goal", method = RequestMethod.POST)
    public Goal addGoal(@Valid @RequestBody Goal goal){
        return goalDao.createGoal(goal);
    }

    @RequestMapping(path = "/profile/goal/{id}", method = RequestMethod.PUT)
    public boolean updateGoal(@PathVariable("id") int profile_id, @Valid @RequestBody Goal goal){
        return goalDao.updateGoal(goal, profile_id);
    }

    @RequestMapping(path = "/profile/goal/{id}", method = RequestMethod.GET)
    public Goal getGoalByProfileId(@PathVariable("id") int profile_id){
        return goalDao.findGoalByProfileId(profile_id);
    }


    @RequestMapping(path = "/profile/goal/{id}", method = RequestMethod.DELETE)
    public void deleteGoal(@PathVariable("id") int profile_id){
        goalDao.deleteGoal(profile_id);
    }
}
