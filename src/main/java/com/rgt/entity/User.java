package com.rgt.entity;

import java.util.ArrayList;
import java.util.List;

public class User {
    private String username;
    private String password;
    private String name;
    private String bio;
    private List<String> followings;
    private List<String> followers;
    private List<Tweet> tweets;

    public User(String username, String password, String name,String bio) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.bio = bio;
        this.followings = new ArrayList<String>();
        this.followers = new ArrayList<String>();
        this.tweets = new ArrayList<Tweet>();
    }

    // Getters and Setters

	public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }

    public String getBio() {
        return bio;
    }

    public List<String> getFollowings() {
        return followings;
    }

    public List<String> getFollowers() {
        return followers;
    }

    public List<Tweet> getTweets() {
        return tweets;
    }

    // Methods

    public void follow(String username) {
        followings.add(username);
    }

    public void unfollow(String username) {
        followings.remove(username);
    }

    public void postTweet(Tweet tweet) {
        tweets.add(tweet);
    }

    public void deleteTweet(Tweet tweet) {
        tweets.remove(tweet);
    }
}
