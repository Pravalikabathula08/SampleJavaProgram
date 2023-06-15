package com.rgt.datastructure;

import java.util.Scanner;

import com.rgt.entity.Tweet;
import com.rgt.entity.User;
import com.rgt.message.RGTMessaging;

public class RgtmainExample {
	private static RGTMessaging messagingApp;

	public static void main(String[] args) {
		messagingApp = new RGTMessaging();
		messagingApp.loadData();

		Scanner scanner = new Scanner(System.in);

		System.out.println("Welcome to RGT Messaging!");

		boolean isLoggedIn = false;

		while (true) {
			if (!isLoggedIn) {
				System.out.println("\nPlease select an option:");
				System.out.println("1. Register");
				System.out.println("2. Login");
				System.out.println("0. Exit");
			} else {
				System.out.println("\nPlease select an option:");
				System.out.println("1. Logout");
				System.out.println("2. Follow User");
				System.out.println("3. Unfollow User");
				System.out.println("4. Post Tweet");
				System.out.println("5. Delete Tweet");
				System.out.println("6. Search User");
				System.out.println("7. Search Tweet");
				System.out.println("8. View Timeline");
				System.out.println("9. View Profile");
				System.out.println("0. Exit");
			}

			int option = scanner.nextInt();
			scanner.nextLine(); // Consume newline character

			if (!isLoggedIn) {
				switch (option) {
				case 1:
					registerUser(scanner);
					break;
				case 2:
					isLoggedIn = loginUser(scanner);
					break;
				case 0:
					System.out.println("Thank you for using RGT Messaging. Goodbye!");
					messagingApp.saveData();
					scanner.close();
					System.exit(0);
					break;
				default:
					System.out.println("Invalid option. Please try again.");
				}
			} else {
				switch (option) {
				case 1:
					messagingApp.logout();
					isLoggedIn = false;
					break;
				case 2:
					followUser(scanner);
					break;
				case 3:
					unfollowUser(scanner);
					break;
				case 4:
					postTweet(scanner);
					break;
				case 5:
					deleteTweet(scanner);
					break;
				case 6:
					searchUser(scanner);
					break;
				case 7:
					searchTweet(scanner);
					break;
				case 8:
					viewTimeline();
					break;
				case 9:
					viewProfile();
					break;
				case 0:
					System.out.println("Thank you for using RGT Messaging. Goodbye!");
					messagingApp.saveData();
					scanner.close();
					System.exit(0);
					break;
				default:
					System.out.println("Invalid option. Please try again.");
				}
			}
		}
	}

	private static void registerUser(Scanner scanner) {
		System.out.println("\nPlease enter a username:");
		String username = scanner.nextLine();

		System.out.println("Please enter a password:");
		String password = scanner.nextLine();

		System.out.println("Please enter your name:");
		String name = scanner.nextLine();

		System.out.println("Please enter your bio:");
		String bio = scanner.nextLine();

		messagingApp.registerUser(username, password, name, bio);
		System.out.println("Registration successful!");
	}

	private static boolean loginUser(Scanner scanner) {
		System.out.println("\nPlease enter your username:");
		String username = scanner.nextLine();

		System.out.println("Please enter your password:");
		String password = scanner.nextLine();

		if (messagingApp.login(username, password)) {
			System.out.println("Login successful!");
			System.out.println("Welcome," + messagingApp.getCurrentUser().getName() + "!");
			return true;
		} else {
			System.out.println("Invalid username or password. Login failed.");
			return false;
		}
	}

	private static void followUser(Scanner scanner) {
		System.out.println("\nPlease enter the username of the user you want to follow:");
		String username = scanner.nextLine();

		if (messagingApp.follow(username)) {
			System.out.println("You are now following @" + username + "!");
		} else {
			System.out.println("Unable to follow @" + username + ". User not found or already being followed.");
		}
	}

	private static void unfollowUser(Scanner scanner) {
		System.out.println("\nPlease enter the username of the user you want to unfollow:");
		String username = scanner.nextLine();

		if (messagingApp.unfollow(username)) {
			System.out.println("You have unfollowed @" + username + ".");
		} else {
			System.out.println("Unable to unfollow @" + username + ". User not found or not being followed.");
		}
	}

	private static void postTweet(Scanner scanner) {
		System.out.println("\nPlease enter your tweet:");
		String content = scanner.nextLine();

		if (messagingApp.postTweet(content)) {
			System.out.println("Tweet posted successfully!");
		} else {
			System.out.println("Unable to post tweet. You must be logged in to post a tweet.");
		}
	}

	private static void deleteTweet(Scanner scanner) {
		System.out.println("\nPlease enter the ID of the tweet you want to delete:");
		String tweetId = scanner.nextLine();

		if (messagingApp.deleteTweet(tweetId)) {
			System.out.println("Tweet deleted successfully!");
		} else {
			System.out.println("Unable to delete tweet. Tweet not found or you are not the author of the tweet.");
		}
	}

	private static void searchUser(Scanner scanner) {
		System.out.println("\nPlease enter the username of the user you want to search:");
		String username = scanner.nextLine();

		User user = messagingApp.searchUser(username);

		if (user != null) {
			System.out.println("User found:");
			System.out.println("Username: " + user.getUsername());
			System.out.println("Name: " + user.getName());
			System.out.println("Bio: " + user.getBio());
		} else {
			System.out.println("User not found.");
		}
	}

	private static void searchTweet(Scanner scanner) {
		System.out.println("\nPlease enter the ID of the tweet you want to search:");
		String tweetId = scanner.nextLine();

		Tweet tweet = messagingApp.searchTweet(tweetId);

		if (tweet != null) {
			System.out.println("Tweet found:");
			System.out.println("ID: " + tweet.getId());
			System.out.println("Content: " + tweet.getContent());
			System.out.println("Author: @" + tweet.getAuthor());
			System.out.println("Timestamp: " + tweet.getTimestamp());
		} else {
			System.out.println("Tweet not found.");
		}
	}

	private static void viewTimeline() {
		System.out.println("\nTimeline:");

		User currentUser = messagingApp.getCurrentUser();
		if (currentUser != null) {
			for (Tweet tweet : messagingApp.getTimeline()) {
				System.out.println("ID: " + tweet.getId());
				System.out.println("Content: " + tweet.getContent());
				System.out.println("Author: @" + tweet.getAuthor());
				System.out.println("Timestamp: " + tweet.getTimestamp());
				System.out.println();
			}
		} else {
			System.out.println("You must be logged in to view the timeline.");
		}
	}

	private static void viewProfile() {
		User currentUser = messagingApp.getCurrentUser();

		if (currentUser != null) {
			System.out.println("\nProfile:");
			System.out.println("Username: " + currentUser.getUsername());
			System.out.println("Name: " + currentUser.getName());
			System.out.println("Bio: " + currentUser.getBio());
			System.out.println("\nTweets:");
			for (Tweet tweet : currentUser.getTweets()) {
				System.out.println("ID: " + tweet.getId());
				System.out.println("Content: " + tweet.getContent());
				System.out.println("Timestamp: " + tweet.getTimestamp());
				System.out.println();
			}
		} else {
			System.out.println("You must be logged in to view your profile.");
		}
	}
}
