package com.rgt.samples;

public class Patron {

	private String name;
	private Book borrowedBook;

	public Patron(String name) {
		this.name = name;
		this.borrowedBook = null;
	}

	public String getName() {
		return name;
	}

	public void borrowBook(Book book) {
		borrowedBook = book;
	}

	public void returnBook(Book book) {
		borrowedBook = null;
	}
	public Book getBorrowedBook() {
		return borrowedBook;
	}

	public void setBorrowedBook(Book borrowedBook) {
		this.borrowedBook = borrowedBook;
	}

	public void setName(String name) {
		this.name = name;
	}
}
