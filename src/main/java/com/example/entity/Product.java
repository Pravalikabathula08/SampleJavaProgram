package com.example.entity;

import java.io.Serializable;

public class Product implements Serializable {
	private String name;
	private String description;
	private double price;
	private int quantity;

	public Product(String name, String description, double price, int quantity) {
		this.name = name;
		this.description = description;
		this.price = price;
		this.quantity = quantity;
	}

	// Getters and setters for the fields

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	// Override toString() method for printing the product details

	@Override
	public String toString() {
		return "Product{" + "name='" + name + '\'' + ", description='" + description + '\'' + ", price=" + price
				+ ", quantity=" + quantity + '}';
	}
}