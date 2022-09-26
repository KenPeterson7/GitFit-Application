package com.techelevator.dao;

import com.techelevator.model.Profile;

public interface ProfileDao {

    boolean createProfile(Profile profile);

    boolean updateProfile(Profile profile);
}
