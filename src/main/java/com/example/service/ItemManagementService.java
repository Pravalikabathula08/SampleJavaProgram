package com.example.service;

import java.util.ArrayList;
import java.util.List;

import com.example.designpatterns.Auction;
import com.example.entity.Bid;
import com.example.entity.Item;
import com.example.entity.User;

public class ItemManagementService {

	private Auction auction;
	private List<Item> items;

	/**
	 * Constructs an ItemManagementService instance. Initializes the auction and
	 * items list.
	 */
	public ItemManagementService() {
		this.auction = Auction.getInstance();
		this.items = new ArrayList<>();
	}

	/**
	 * Adds a new item to the auction with the specified details.
	 *
	 * @param name        The name of the item.
	 * @param description The description of the item.
	 * @param startingBid The starting bid for the item.
	 * @return The newly added item.
	 */
	public Item addItem(String name, String description, double startingBid) {
		Item item = new Item(name, description, startingBid);
		items.add(item);
		return item;
	}

	/**
	 * Searches for items containing the specified keyword in their names.
	 *
	 * @param keyword The keyword to search for.
	 * @return A list of items matching the search criteria.
	 */
	public List<Item> searchItems(String keyword) {
		List<Item> searchResults = new ArrayList<>();
		for (Item item : items) {
			if (item.getName().contains(keyword)) {
				searchResults.add(item);
			}
		}

		return searchResults;
	}

	/**
	 * Retrieves an item by its name.
	 *
	 * @param itemName The name of the item to retrieve.
	 * @return The item with the specified name, or null if not found.
	 */

	public Item getItemByName(String itemName) {
		for (Item item : items) {
			if (item.getName().equals(itemName)) {
				return item;
			}
		}

		return null;
	}

	/**
	 * Adds a new item to the auction.
	 *
	 * @param newItem The item to add.
	 */
	public void addItem(Item newItem) {
		items.add(newItem);
	}

	/**
	 * Retrieves the bidding history of a user.
	 *
	 * @param user The user for whom to retrieve the bidding history.
	 * @return A list of bids made by the user.
	 */
	public List<Bid> viewBiddingHistory(User user) {
		List<Bid> bidHistory = new ArrayList<>();
		for (Item item : items) {
			List<Bid> itemBid = item.getBids();
			for (Bid bid : itemBid) {
				if (bid.getBidder().equals(user)) {
					bidHistory.add(bid);
				}
			}

		}

		return bidHistory;
	}
}