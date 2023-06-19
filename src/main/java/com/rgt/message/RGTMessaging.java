package com.rgt.message;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.rgt.entity.Tweet;
import com.rgt.entity.User;

public class RGTMessaging {
	private Map<String, User> users;
	private List<Tweet> tweets;
	private User currentUser;

	public RGTMessaging() {
		this.users = new HashMap<String, User>();
		this.tweets = new ArrayList<Tweet>();
		this.currentUser = null;
	}

	// Registers a new user
	public void registerUser(String username, String password, String name, String bio) {
		if (!users.containsKey(username)) {
			User user = new User(username, password, name, bio);
			users.put(username, user);

		}
	}

	// Logs in user
	public boolean login(String username, String password) {
		if (users.containsKey(username)) {
			User user = users.get(username);
			if (user.getPassword().equals(password)) {
				currentUser = user;
				return true;
			}
		}
		return false;
	}

	// Log out the currently logged-in user
	public void logout() {
		currentUser = null;
	}

	public boolean follow(String username) {
		if (users.containsKey(username)) {
			User userToFollow = users.get(username);
			if (!currentUser.getFollowings().contains(username)) {
				currentUser.follow(username);
				userToFollow.getFollowers().add(currentUser.getUsername());
				return true;
			}
		}
		return false;
	}

	// Unfollows the user with the particular username
	public boolean unfollow(String username) {
		if (users.containsKey(username)) {
			User userToUnfollow = users.get(username);
			if (currentUser.getFollowings().contains(username)) {
				currentUser.unfollow(username);
				userToUnfollow.getFollowers().remove(currentUser.getUsername());
				return true;
			}
		}
		return false;
	}

	// Post a tweet
	public boolean postTweet(String content) {
		if (currentUser != null) {
			Tweet tweet = new Tweet(content, currentUser.getUsername(), getCurrentTimestamp());
			currentUser.postTweet(tweet);
			tweets.add(tweet);
			return true;
		}
		return false;
	}

	// Delete particular tweet ID
	public boolean deleteTweet(String tweetId) {
		int id = Integer.parseInt(tweetId);
		for (Tweet tweet : tweets) {
			if (tweet.getId() == id && tweet.getAuthor().equals(currentUser.getUsername())) {
				currentUser.deleteTweet(tweet);
				tweets.remove(tweet);
				return true;
			}
		}
		return false;
	}

	// Search particular User
	public User searchUser(String username) {
		if (users.containsKey(username)) {
			return users.get(username);
		}
		return null;
	}

	// Search particular
	public Tweet searchTweet(String tweetId) {
		int id = Integer.parseInt(tweetId);
		for (Tweet tweet : tweets) {
			if (tweet.getId() == id) {
				return tweet;
			}
		}
		return null;
	}

	// Get the timeline
	public List<Tweet> getTimeline() {
		List<Tweet> timeline = new ArrayList<Tweet>();
		for (String username : currentUser.getFollowings()) {
			User user = users.get(username);
			timeline.addAll(user.getTweets());
		}
		return timeline;
	}

	public User getCurrentUser() {
		return currentUser;
	}

	public void saveData() {
		List<String> userData = new ArrayList<String>();
		for (User user : users.values()) {
			String userString = user.getUsername() + "," + user.getPassword() + "," + user.getName();
			userData.add(userString);
		}
		DataStore.saveUserData(userData);

		List<String> tweetData = new ArrayList<String>();
		for (Tweet tweet : tweets) {
			String tweetString = tweet.getId() + "," + tweet.getContent() + "," + tweet.getAuthor() + ","
					+ tweet.getTimestamp();
			tweetData.add(tweetString);
		}
		DataStore.saveTweetData(tweetData);
	}

	public void loadData() {
		List<String> userData = DataStore.loadUserData();
		for (String userString : userData) {
			String[] userInfo = userString.split(",");
			String username = userInfo[0];
			String password = userInfo[1];
			String name = userInfo[2];
			String bio = userInfo[3];
			User user = new User(username, password, name, bio);
			users.put(username, user);
		}

		List<String> tweetData = DataStore.loadTweetData();
		for (String tweetString : tweetData) {
			String[] tweetInfo = tweetString.split(",");
			int id = Integer.parseInt(tweetInfo[0]);
			String content = tweetInfo[1];
			String author = tweetInfo[2];
			String timestamp = tweetInfo[3];
			Tweet tweet = new Tweet(content, author, timestamp);
			tweet.setId(id);
			tweets.add(tweet);
		}
	}

	/**
	 * Gets the current timestamp in the format "yyyy-MM-dd HH:mm:ss".
	 * 
	 * @return The current timestamp as a string.
	 */
	private String getCurrentTimestamp() {
		LocalDateTime currentTime = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		return currentTime.format(formatter);

	}

}
