package com.rgt.message;

import java.util.ArrayList;
import java.util.List;

public class DataStore {

	private static List<String> userData = new ArrayList<String>();
	private static List<String> tweetData = new ArrayList<String>();

	/**
	 * Saves the user data to the data store.
	 *
	 * @param data The list of user data to be saved.
	 */
	public static void saveUserData(List<String> data) {
		userData = new ArrayList<String>(data);
	}

	/**
	 * Saves the tweet data to the data store.
	 *
	 * @param data The list of tweet data to be saved.
	 */

	public static void saveTweetData(List<String> data) {
		tweetData = new ArrayList<String>(data);
	}

	/**
	 * Loads the user data from the data store.
	 *
	 * @return The list of loaded user data.
	 */
	public static List<String> loadUserData() {
		return new ArrayList<String>(userData);
	}

	/**
	 * Loads the tweet data from the data store.
	 *
	 * @return The list of loaded tweet data.
	 */
	public static List<String> loadTweetData() {
		return new ArrayList<String>(tweetData);
	}
}
