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
    public boolean updateProfile(@PathVariable("id") int profile_id, @Valid @RequestBody Profile profile){
        return profileDao.updateProfile(profile, profile_id);
    }
    @RequestMapping(path = "/profile/{username}", method = RequestMethod.GET)
    public Profile findProfile(@PathVariable("username") String username){
        return profileDao.findProfileByUsername(username);
    }

    @RequestMapping(path = "/profile/currentStar/{id}", method = RequestMethod.PUT)
    public boolean updateCurrentStar(@PathVariable("id") int profileId, @Valid @RequestBody Profile profile){
        return profileDao.updateCurrentStar(profile, profileId);
    }
    @RequestMapping(path = "/profile/highestStar/{id}", method = RequestMethod.PUT)
    public boolean updateHighestStar(@PathVariable("id") int profileId, @Valid @RequestBody Profile profile){
        return profileDao.updateHighestStar(profile, profileId);
    }

}
