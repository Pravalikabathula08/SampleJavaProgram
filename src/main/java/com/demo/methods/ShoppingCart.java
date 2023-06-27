package com.demo.methods;

import java.util.HashMap;

import com.example.entity.Product;

public class ShoppingCart {

	private HashMap<Product, Integer> items;

	public ShoppingCart() {
		this.items = new HashMap<>();
	}

	/**
	 * Returns the items in the shopping cart along with their quantities.
	 *
	 * @return a HashMap containing the items in the shopping cart as keys and their
	 *         corresponding quantities as values.
	 */
	public HashMap<Product, Integer> getItems() {
		return items;
	}

	/**
	 * Adds a product to the shopping cart with the specified quantity.
	 *
	 * @param product  the product to add.
	 * @param quantity the quantity of the product to add.
	 */
	public void addItem(Product product, int quantity) {
		if (items.containsKey(product)) {
			int existingQuantity = items.get(product);
			items.put(product, existingQuantity + quantity);
		} else {
			items.put(product, quantity);
		}
	}

	/**
	 * Removes a product from the shopping cart.
	 *
	 * @param product the product to remove.
	 */
	public void removeItem(Product product) {
		items.remove(product);
	}

	/**
	 * Calculates and returns the total price of all items in the shopping cart.
	 *
	 * @return the total price of all items in the shopping cart.
	 */
	public double getTotalPrice() {
		double totalPrice = 0.0;
		for (Product product : items.keySet()) {
			int quantity = items.get(product);
			totalPrice += product.getPrice() * quantity;
		}
		return totalPrice;
	}

	public int getQuantity(Product product) {
		// TODO Auto-generated method stub
		return 0;
	}
}