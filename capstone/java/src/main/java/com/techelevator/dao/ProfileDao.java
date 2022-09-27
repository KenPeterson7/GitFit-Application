package com.techelevator.dao;

import com.techelevator.model.Profile;

public interface ProfileDao {

    Profile createProfile(Profile profile);

    boolean updateProfile(Profile profile, int profileId);
}
