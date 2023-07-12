package com.rgt.library.entity;

public class Resource {
	private String title;
	private String author;
	private String type;

	public Resource(String title, String author, String type) {
		this.title = title;
		this.author = author;
		this.type = type;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return title + "," + author + "," + type;
	}

	public String[] toCSV() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getAdditionalInfo() {
		// TODO Auto-generated method stub
		return null;
	}
}
