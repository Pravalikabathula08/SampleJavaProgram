package com.rgt.library.entity;

public class CD extends Resource {
	private String genre;

	public CD(String title, String author, String genre) {
		super(title, author, "CD");
		this.genre = genre;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	@Override
	public String toString() {
		return getTitle() + "," + getAuthor() + "," + getType() + "," + getGenre();
	}
}
