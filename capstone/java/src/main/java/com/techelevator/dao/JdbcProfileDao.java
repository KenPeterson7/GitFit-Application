package com.techelevator.dao;

import com.techelevator.model.Profile;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

import java.util.Objects;

@Repository
public class JdbcProfileDao implements ProfileDao{

    private JdbcTemplate jdbcTemplate;

    public JdbcProfileDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public boolean createProfile(Profile profile) {
        String sql = "insert into Profile(age, height, current_weight, desired_weight, birthday, profile_pic, current_star_streak, high_start_streak, username)\n" +
                "values(?, ?, ?, ?, ?, ?, ?, ?, ?)";
        SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sql, profile);
        return true;
    }

    @Override
    public boolean updateProfile(Profile profile) {
        return false;
    }

    private Profile mapRowToProfile(SqlRowSet rs){
        Profile profile = new Profile();
        profile.setProfileId(rs.getInt("profile_id"));
        profile.setAge(rs.getInt("age"));
        profile.setHeight(rs.getInt("height"));
        profile.setCurrentWeight(rs.getInt("current_weight"));
        profile.setDesiredWeight(rs.getInt("desired_weight"));
        profile.setBirthday(Objects.requireNonNull(rs.getDate("birthday")).toLocalDate());
        profile.setProfilePic(rs.getString("profile_pic"));
        profile.setStarStreak(rs.getInt("current_star_streak"));
        profile.setHighStarStreak(rs.getInt("high_star_streak"));
        profile.setUsername(rs.getString("username"));
        return profile;
    }
}
