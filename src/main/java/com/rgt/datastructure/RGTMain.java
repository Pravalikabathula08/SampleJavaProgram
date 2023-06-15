
package com.rgt.datastructure;

import java.util.Scanner;

import com.rgt.entity.Tweet;
import com.rgt.entity.User;
import com.rgt.message.RGTMessaging;

public class RGTMain {
	private static RGTMessaging messaging;

	public static void main(String[] args) {
		messaging = new RGTMessaging();
		messaging.loadData(); // Load previously saved data
		System.out.println("Welcome to RGT Messaging!");
		Scanner scanner = new Scanner(System.in);
		boolean loggedIn = false;
		while (true) {
			System.out.println("Enter 'register' to create a new account, 'login' to sign in, or 'exit' to quit:");
			String input = scanner.nextLine().trim().toLowerCase();

			if (input.equals("register")) {
				registerUser(scanner);
			} else if (input.equals("login")) {
				loggedIn = loginUser(scanner);
				if (loggedIn) {
					handleUserActions(scanner);
					break;
				}
			} else if (input.equals("exit")) {
				break;
			} else {
				System.out.println("Invalid input. Please try again.");
			}
		}

		messaging.saveData(); // Save data before exiting scanner.close();
		System.out.println("Goodbye!");
	}

	/**
	 * 
	 * @param scanner
	 */
	private static void registerUser(Scanner scanner) {
		System.out.println("Enter a username:");
		String username = scanner.nextLine().trim();

		System.out.println("Enter a password:");
		String password = scanner.nextLine().trim();

		System.out.println("Enter your name:");
		String name = scanner.nextLine().trim();

		System.out.println("Enter your bio:");
		String bio = scanner.nextLine().trim();

		messaging.registerUser(username, password, name, bio);
		System.out.println("Registration successful! You can now log in.");
	}

	private static boolean loginUser(Scanner scanner) {
		System.out.println("Enter your username:");
		String username = scanner.nextLine().trim();

		System.out.println("Enter your password:");
		String password = scanner.nextLine().trim();

		boolean loggedIn = messaging.login(username, password);
		if (loggedIn) {
			System.out.println("Login successful!");
		} else {
			System.out.println("Invalid username or password. Please try again.");
		}
		return loggedIn;
	}

	/**
	 * 
	 * @param scanner
	 */
	private static void handleUserActions(Scanner scanner) {
		while (true) {
			System.out.println("\nEnter 'follow' to follow a user, 'unfollow' to unfollow, 'tweet' to post a tweet,");
			System.out.println(
					"'delete' to delete a tweet, 'search user' to find a user, 'search tweet' to find a tweet,");
			System.out.println(
					"'timeline' to view your timeline, 'profile' to view your profile, or 'logout' to sign out:");

			String input = scanner.nextLine().trim().toLowerCase();

			if (input.equals("follow")) {
				followUser(scanner);
			} else if (input.equals("unfollow")) {
				unfollowUser(scanner);
			} else if (input.equals("tweet")) {
				postTweet(scanner);
			} else if (input.equals("delete")) {
				deleteTweet(scanner);
			} else if (input.equals("search user")) {
				searchUser(scanner);
			} else if (input.equals("search tweet")) {
				searchTweet(scanner);
			} else if (input.equals("timeline")) {
				displayTimeline();
			} else if (input.equals("profile")) {
				displayProfile();
			} else if (input.equals("logout")) {
				messaging.logout();
				System.out.println("Logged out successfully.");
				break;
			} else {
				System.out.println("Invalid input. Please try again.");
			}
		}
	}

	/**
	 * 
	 * @param scanner
	 */
	private static void followUser(Scanner scanner) {
		System.out.println("Enter the username of the user you want to follow:");
		String username = scanner.nextLine().trim();

		boolean success = messaging.follow(username);
		if (success) {
			System.out.println("You are now following " + username + ".");
		} else {
			System.out.println("User " + username + " not found.");
		}
	}

	private static void unfollowUser(Scanner scanner) {
		System.out.println("Enter the username of the user you want to unfollow:");
		String username = scanner.nextLine().trim();

		boolean success = messaging.unfollow(username);
		if (success) {
			System.out.println("You have unfollowed " + username + ".");
		} else {
			System.out.println("You were not following " + username + ".");
		}
	}

	private static void postTweet(Scanner scanner) {
		System.out.println("Enter your tweet content:");
		String content = scanner.nextLine().trim();

		boolean success = messaging.postTweet(content);
		if (success) {
			System.out.println("Tweet posted successfully.");
		} else {
			System.out.println("Failed to post tweet.");
		}
	}

	/**
	 * 
	 * @param scanner
	 */
	private static void deleteTweet(Scanner scanner) {
		System.out.println("Enter the ID of the tweet you want to delete:");
		String tweetId = scanner.nextLine().trim();

		boolean success = messaging.deleteTweet(tweetId);
		if (success) {
			System.out.println("Tweet deleted successfully.");
		} else {
			System.out.println("Tweet not found or you do not have permission to delete it.");
		}
	}

	/**
	 * 
	 * @param scanner
	 */
	private static void searchUser(Scanner scanner) {
		System.out.println("Enter the username of the user you want to search for:");
		String username = scanner.nextLine().trim();

		User user = messaging.searchUser(username);
		if (user != null) {
			System.out.println("Username: " + user.getUsername());
			System.out.println("Name: " + user.getName());
			System.out.println("Bio: " + user.getBio());
			System.out.println("Followers: " + user.getFollowers());
			System.out.println("Followings: " + user.getFollowings());
		} else {
			System.out.println("User " + username + " not found.");
		}
	}

	/**
	 * 
	 * @param scanner
	 */
	private static void searchTweet(Scanner scanner) {
		System.out.println("Enter the ID of the tweet you want to search for:");
		String tweetId = scanner.nextLine().trim();

		Tweet tweet = messaging.searchTweet(tweetId);
		if (tweet != null) {
			System.out.println("Tweet ID: " + tweet.getId());
			System.out.println("Content: " + tweet.getContent());
			System.out.println("Author: " + tweet.getAuthor());
			System.out.println("Timestamp: " + tweet.getTimestamp());
		} else {
			System.out.println("Tweet not found.");
		}
	}

	private static void displayTimeline() {
		System.out.println("Timeline:");

		for (Tweet tweet : messaging.getTimeline()) {
			System.out.println("Tweet ID: " + tweet.getId());
			System.out.println("Content: " + tweet.getContent());
			System.out.println("Author: " + tweet.getAuthor());
			System.out.println("Timestamp: " + tweet.getTimestamp());
			System.out.println("--------------------");
		}
	}

	private static void displayProfile() {
		User currentUser = messaging.getCurrentUser();

		System.out.println("Username: " + currentUser.getUsername());
		System.out.println("Name: " + currentUser.getName());
		System.out.println("Bio: " + currentUser.getBio());
		System.out.println("Followers: " + currentUser.getFollowers());
		System.out.println("Followings: " + currentUser.getFollowings());
		System.out.println("Tweets:");

		for (Tweet tweet : currentUser.getTweets()) {
			System.out.println("Tweet ID: " + tweet.getId());
			System.out.println("Content: " + tweet.getContent());
			System.out.println("Timestamp: " + tweet.getTimestamp());
			System.out.println("--------------------");
		}
	}
}
