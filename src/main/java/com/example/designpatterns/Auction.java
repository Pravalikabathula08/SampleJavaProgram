package com.example.designpatterns;

import java.util.ArrayList;
import java.util.List;

import com.example.entity.Item;

public class Auction {

	private static Auction instance = null;
	private List<Item> items;

	/**
	 * Private constructor to enforce singleton pattern.
	 */
	private Auction() {
		items = new ArrayList<>();
	}

	/**
	 * Returns the instance of the Auction class.
	 *
	 * @return The instance of the Auction class.
	 */
	public static Auction getInstance() {
		if (instance == null) {
			instance = new Auction();
		}
		return instance;
	}

	/**
	 * Returns the list of items in the auction.
	 *
	 * @return The list of items in the auction.
	 */
	public List<Item> getItems() {
		return items;
	}

	/**
	 * Sets the list of items in the auction.
	 *
	 * @param items The list of items to be set in the auction.
	 */
	public void setItems(List<Item> items) {
		this.items = items;
	}

	/**
	 * Adds an item to the auction.
	 *
	 * @param item The item to be added to the auction.
	 */
	public void addItem(Item item) {
		items.add(item);
	}

}
