package com.techelevator.controller;

import com.techelevator.dao.ProfileDao;
import com.techelevator.model.Profile;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin
@RestController
public class ProfileController {

    private ProfileDao profileDao;

    public ProfileController(ProfileDao profileDao) {
        this.profileDao = profileDao;
    }

    @RequestMapping(path = "/profile", method = RequestMethod.POST)
    public Profile addProfile(@Valid @RequestBody Profile profile){
        return profileDao.createProfile(profile);
    }

    @RequestMapping(path = "/profile/{id}", method = RequestMethod.PUT)
    public boolean updateProfile(@PathVariable("profile_id") int profileId, @Valid @RequestBody Profile profile){
        return profileDao.updateProfile(profile, profileId);
    }



//    @RequestMapping(path = "/food/{id}", method = RequestMethod.PUT)
//    public boolean modifyFood(@PathVariable("foodId") int foodId, @Valid @RequestBody Food modifiedFood) {
//
//        return foodDao.modifyFood(foodId, modifiedFood);
//    }
}
