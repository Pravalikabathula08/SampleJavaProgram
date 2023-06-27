package com.demo.methods;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import com.example.entity.Order;

public class OrderHistory {
	private List<Order> orders;

	/**
	 * Constructs an empty OrderHistory.
	 */
	public OrderHistory() {
		this.orders = new ArrayList<>();
	}

	/**
	 * Returns all the orders in the order history.
	 *
	 * @return a List containing all the orders in the order history.
	 */
	public List<Order> getAllOrders() {
		return orders;
	}

	/**
	 * Adds an order to the order history.
	 *
	 * @param order the order to add.
	 */
	public void addOrder(Order order) {
		orders.add(order);
	}

	/**
	 * Saves the order history to a file.
	 *
	 * @param fileName the name of the file to save the order history to.
	 */
	public void saveOrderHistory(String fileName) {
		try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName))) {
			oos.writeObject(orders);
			System.out.println("Order history saved successfully.");
		} catch (IOException e) {
			System.out.println("Error saving order history: " + e.getMessage());
		}
	}

	/**
	 * Loads the order history from a file.
	 *
	 * @param fileName the name of the file to load the order history from.
	 */
	public void loadOrderHistory(String fileName) {
		orders.clear();
		try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName))) {
			orders = (List<Order>) ois.readObject();
			System.out.println("Order history loaded successfully.");
		} catch (IOException | ClassNotFoundException e) {
			System.out.println("Error loading order history: " + e.getMessage());
		}
	}
}