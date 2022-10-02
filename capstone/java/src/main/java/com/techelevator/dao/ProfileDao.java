package com.techelevator.dao;

import com.techelevator.model.Profile;

public interface ProfileDao {

    Profile createProfile(Profile profile);

    boolean updateProfile(Profile profile, int profileId);

    Profile findProfileByUsername(String username);

    boolean updateCurrentStar(Profile profile, int profileId);

    boolean updateHighestStar(Profile profile, int profileId);
}
