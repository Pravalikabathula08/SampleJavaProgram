package com.example.entity;

import java.io.Serializable;
import java.util.HashMap;

public class Order implements Serializable {
	private String confirmationNumber;
	private HashMap<Product, Integer> items;
	private double totalPrice;

	public Order() {
	}

	// constructor
	public Order(String confirmationNumber, HashMap<Product, Integer> items, double totalPrice) {
		this.confirmationNumber = confirmationNumber;
		this.items = items;
		this.totalPrice = totalPrice;
	}

	public Order(HashMap<Product, Integer> items) {
	}

	// Getters and setters

	public String getConfirmationNumber() {
		return confirmationNumber;
	}

	public void setConfirmationNumber(String confirmationNumber) {

		this.confirmationNumber = confirmationNumber;
	}

	public HashMap<Product, Integer> getItems() {
		return items;
	}

	public void setItems(HashMap<Product, Integer> items) {

		this.items = items;
	}

	public double getTotalPrice() {

		return totalPrice;
	}

	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}

	// Override toString() method for printing the Order Details
	@Override
	public String toString() {
		return "Order{" + "confirmationNumber='" + confirmationNumber + '\'' + ", items=" + items + ", totalPrice="
				+ totalPrice + '}';
	}
}
