package com.example.onlinebidding;

import java.util.List;
import java.util.Scanner;

import com.example.classes.AutomaticBiddingStrategy;
import com.example.classes.IncrementalBiddingStrategy;
import com.example.entity.Bid;
import com.example.entity.Item;
import com.example.entity.User;
import com.example.interfaces.BiddingStrategy;
import com.example.service.ItemManagementService;
import com.example.service.NotificationService;
import com.example.service.UserManagementService;

public class BiddingSystem {
	private static UserManagementService userManagementService = new UserManagementService();
	private static ItemManagementService itemManagementService = new ItemManagementService();
	private static NotificationService notificationService = new NotificationService();
	private static User currentUser;

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		while (true) {
			System.out.println("Welcome to the Online Bidding System !");
			System.out.println("1.Create Account");
			System.out.println("2.Login");
			System.out.println("3. Exit");

			System.out.print("Enter your choice: ");
			int option = scanner.nextInt();
			scanner.nextLine();

			switch (option) {
			case 1:
				createAccount(scanner);
				break;
			case 2:
				login(scanner);
				break;
			case 3:
				System.out.println("Goodbye!");
				System.exit(0);
				break;
			default:
				System.out.println("Invalid option. Please try again");
				break;
			}
		}
	}

	/**
	 * Creates a new account for the user.
	 *
	 * @param scanner The scanner object used for user input.
	 */
	private static void createAccount(Scanner scanner) {
		System.out.println("Creating a new account...");
		System.out.print("Enter username: ");
		String username = scanner.nextLine();
		System.out.print("Enter password: ");
		String password = scanner.nextLine();
		System.out.println("Account created successfully.");
	}

	/**
	 * Logs in the user to the bidding system.
	 *
	 * @param scanner The scanner object used for user input.
	 */
	private static void login(Scanner scanner) {
		System.out.println(".....UserLogging in...");
		System.out.print("Enter username: ");
		String username = scanner.nextLine();
		System.out.print("Enter password: ");
		String password = scanner.nextLine();
		currentUser = userManagementService.login(username, password);

		if (currentUser != null) {
			System.out.println("Login successfully...");
			showLoggedInMenu(scanner);
		} else {
			System.out.println("Invalid credentials Please try again.");
		}
	}

	/**
	 * Displays the menu for the logged-in user.
	 *
	 * @param scanner The scanner object used for user input.
	 */
	private static void showLoggedInMenu(Scanner scanner) {
		while (true) {
			System.out.println("-----------Online Bidding System------------");
			System.out.println("1.addItems");
			System.out.println("2. Search Items");
			System.out.println("3. View Bidding History");
			System.out.println("0. Logout");

			System.out.print("Enter your choice: ");
			int choice = scanner.nextInt();
			scanner.nextLine();

			switch (choice) {

			case 1:
				addItems(scanner);
				break;
			case 2:
				searchItems(scanner);
				break;
			case 3:
				viewBiddingHistory();
				break;
			case 0:
				logout();
				return;
			default:
				System.out.println("Invalid choice. Please try again.");
			}
		}
	}

	/**
	 * Adds an item to the bidding system.
	 *
	 * @param scanner The scanner object used for user input.
	 */
	private static void addItems(Scanner scanner) {
		System.out.println("Adding Items");
		System.out.print("Enter item name: ");
		String itemName = scanner.nextLine();
		System.out.print("Enter item description: ");
		String itemDescription = scanner.nextLine();
		System.out.print("Enter initial bid amount: ");
		double initialBidAmount = scanner.nextDouble();
		scanner.nextLine();
		itemManagementService.addItem(itemName, itemDescription, initialBidAmount);
		System.out.println("Item added successfully.");
	}

	/**
	 * Searches for items in the bidding system based on a keyword.
	 *
	 * @param scanner The scanner object used for user input.
	 */
	private static void searchItems(Scanner scanner) {
		System.out.print("Enter a search keyword: ");
		String keyword = scanner.nextLine();
		System.out.println("Search results:");
		List<Item> searchResults = itemManagementService.searchItems(keyword);
		int i = 1;
		for (Item item : searchResults) {
			System.out.println(i + ". " + item.getName() + " - " + item.getDescription() + " - Current highest bid: "
					+ item.getCurrentHighestBid());
			i++;
			System.out.print("Enter an item name to place a bid, or '0' to go back: ");
			String itemName = scanner.next();
			if (itemName.equals("0")) {
				break;
			}
			System.out.print("Enter a bid amount: ");
			double amount = scanner.nextDouble();
			System.out.println("1. Incremental Bidding");
			System.out.println("2. Automatic Bidding");
			System.out.print("Choose a bidding strategy: ");
			int strategyOption = scanner.nextInt();
			BiddingStrategy biddingStrategy;
			if (strategyOption == 1) {
				biddingStrategy = new IncrementalBiddingStrategy();
			} else {
				System.out.println("Enter the maxmium bid amount : Rs");
				double bidAmount = scanner.nextDouble();
				biddingStrategy = new AutomaticBiddingStrategy(bidAmount);
			}

			Item selection = null;
			for (Item items : searchResults) {
				if (items.getName().equalsIgnoreCase(itemName)) {
					selection = items;
					break;
				}
			}
			if (selection != null) {
				double bidamouts = biddingStrategy.bid(item, currentUser);
				System.out.println("Bid placed successfully your bid amount: Rs" + bidamouts);
			} else {
				System.out.println("Item not found.");
			}
			break;
		}
	}

	private static void viewBiddingHistory() {
		System.out.println("Your bidding history:");
		int i = 1;
		List<Bid> biddingHistory = itemManagementService.viewBiddingHistory(currentUser);
		for (Bid bid : biddingHistory) {
			System.out.println(i + ". " + bid.getItem().getName() + " - " + bid.getItem().getDescription()
					+ " - Bid amount: " + bid.getAmount() + " - Winning bid: " + bid.isWinningBid());
			i++;
		}
		System.out.println();
	}

	/**
	 * Logs out the current user from the bidding system.
	 */
	private static void logout() {
		currentUser = null;
		System.out.println("Logged out successfully!");
	}
}