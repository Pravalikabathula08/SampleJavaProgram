package com.example.designpatterns;

import com.example.entity.Item;

public class ItemFactory {

	/**
	 * Creates and returns a new item with the specified details.
	 *
	 * @param name        the name of the item.
	 * @param description The description of the item.
	 * @param startingBid The starting bid for the item.
	 * @return The newly created item.
	 */
	public static Item createItem(String name, String description, double startingBid) {
		return new Item(name, description, startingBid);
	}

}
