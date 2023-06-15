package com.rgt.message;

import java.util.ArrayList;
import java.util.List;

public class DataStore {
	private static List<String> userData = new ArrayList<String>();
    private static List<String> tweetData = new ArrayList<String>();

    public static void saveUserData(List<String> data) {
        userData = new ArrayList<String>(data);
    }

    public static void saveTweetData(List<String> data) {
        tweetData = new ArrayList<String>(data);
    }

    public static List<String> loadUserData() {
        return new ArrayList<String>(userData);
    }

    public static List<String> loadTweetData() {
        return new ArrayList<String>(tweetData);
    }
}
