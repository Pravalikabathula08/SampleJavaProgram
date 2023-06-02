package com.rgt.samples;

public class Book {
	private String title;
	private String author;
	private boolean borrowed;
	private boolean avalilable;

	public Book(String title, String author) {
		this.title = title;
		this.author = author;
		this.borrowed = false;
		this.avalilable=true;
	}

	public String getTitle() {
		return title;
	}

	public String getAuthor() {
		return author;
	}

	public boolean isBorrowed() {
		return borrowed;
	}

	public void setBorrowed(boolean borrowed) {
		this.borrowed = borrowed;
	}

	public boolean isAvalilable() {
		return avalilable;
	}

	public void setAvalilable(boolean avalilable) {
		this.avalilable = avalilable;
	}
}
