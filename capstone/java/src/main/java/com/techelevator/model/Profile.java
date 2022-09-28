package com.techelevator.model;

import java.time.LocalDate;

public class Profile {
    private int profileId;
    private int age;
    private int height;
    private int currentWeight;
    private int desiredWeight;
    private LocalDate birthday;
    private String profilePic;
    private int starStreak;
    private int highStarStreak;
    private String username;

    private String displayName;

    private String gender;

    private String activityLevel;

    public Profile(int profileId, int age, int height, int currentWeight, int desiredWeight, LocalDate birthday, String profilePic, int starStreak, int highStarStreak, String username, String displayName, String gender, String activityLevel) {
        this.profileId = profileId;
        this.age = age;
        this.height = height;
        this.currentWeight = currentWeight;
        this.desiredWeight = desiredWeight;
        this.birthday = birthday;
        this.profilePic = profilePic;
        this.starStreak = starStreak;
        this.highStarStreak = highStarStreak;
        this.username = username;
        this.displayName = displayName;
        this.gender = gender;
        this.activityLevel = activityLevel;
    }

    public Profile() {}

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getCurrentWeight() {
        return currentWeight;
    }

    public void setCurrentWeight(int currentWeight) {
        this.currentWeight = currentWeight;
    }

    public int getDesiredWeight() {
        return desiredWeight;
    }

    public void setDesiredWeight(int desiredWeight) {
        this.desiredWeight = desiredWeight;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public String getProfilePic() {
        return profilePic;
    }

    public void setProfilePic(String profilePic) {
        this.profilePic = profilePic;
    }

    public int getStarStreak() {
        return starStreak;
    }

    public void setStarStreak(int starStreak) {
        this.starStreak = starStreak;
    }

    public int getHighStarStreak() {
        return highStarStreak;
    }

    public void setHighStarStreak(int highStarStreak) {
        this.highStarStreak = highStarStreak;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getProfileId() {
        return profileId;
    }

    public void setProfileId(int profileId) {
        this.profileId = profileId;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getActivityLevel() {
        return activityLevel;
    }

    public void setActivityLevel(String activityLevel) {
        this.activityLevel = activityLevel;
    }
}
