package com.example.entity;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

public class ProductCatalog {

	private HashMap<String, Product> products;

	/**
	 * Constructs an empty ProductCatalog.
	 */
	public ProductCatalog() {
		this.products = new HashMap<>();
	}

	/**
	 * Returns all the products in the catalog.
	 *
	 * @return a HashMap containing all the products in the catalog.
	 */
	public HashMap<String, Product> getAllProducts() {
		return products;
	}

	/**
	 * Adds a product to the catalog.
	 *
	 * @param product the product to add.
	 */
	public void addProduct(Product product) {
		products.put(product.getName(), product);
	}

	/**
	 * Retrieves a product from the catalog based on its name.
	 *
	 * @param name the name of the product to retrieve.
	 * @return the product with the specified name, or null if not found.
	 */
	public Product getProduct(String name) {
		return products.get(name);
	}

	/**
	 * Saves the products in the catalog to a file.
	 *
	 * @param fileName the name of the file to save the products to.
	 */
	public void saveProducts(String fileName) {
		try (PrintWriter writer = new PrintWriter(new FileWriter(fileName))) {
			for (Product product : products.values()) {
				writer.println(product.getName() + "," + product.getDescription() + "," + product.getPrice() + ","
						+ product.getQuantity());
			}
			System.out.println("Product file saved successfully.");
		} catch (IOException e) {
			System.out.println("Error saving product file: " + e.getMessage());
		}
	}

	/**
	 * Loads products from a file and adds them to the catalog.
	 *
	 * @param fileName the name of the file to load products from.
	 */
	public void loadProducts(String fileName) {
		products.clear();
		try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
			String line;
			while ((line = reader.readLine()) != null) {
				String[] data = line.split(",");
				if (data.length == 4) {
					String name = data[0];
					String description = data[1];
					double price = Double.parseDouble(data[2]);
					int quantity = Integer.parseInt(data[3]);
					Product product = new Product(name, description, price, quantity);
					addProduct(product);
				}
			}
			System.out.println("Product file loaded successfully.");
		} catch (IOException e) {
			System.out.println("Error loading product file: " + e.getMessage());
		}
	}
}