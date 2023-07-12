package com.rgt.library.entity;

public class Book extends Resource {
	private String ISBN;

	public Book(String title, String author, String ISBN) {
		super(title, author, "Book");
		this.ISBN = ISBN;
	}

	public String getISBN() {
		return ISBN;
	}

	public void setISBN(String ISBN) {
		this.ISBN = ISBN;
	}

	@Override
	public String toString() {
		return getTitle() + "," + getAuthor() + "," + getType() + "," + getISBN();
	}
}
