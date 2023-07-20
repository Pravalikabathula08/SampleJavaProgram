package com.rgt.test.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Book {

	 @Id
	 @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String title;
	private int publicationYear;
	private String author;
    private boolean active;
	
	// Constructors, getters, setters

	public Book(Long id, String title, int publicationYear, String author) {
		super();
		this.id = id;
		this.title = title;
		this.publicationYear = publicationYear;
		this.author = author;
	}
	
	public Book() {
		
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getPublicationYear() {
		return publicationYear;
	}

	public void setPublicationYear(int publicationYear) {
		this.publicationYear = publicationYear;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public boolean isActive() {
		// TODO Auto-generated method stub
		return true;
	}

	public void setActive(boolean active) {
		this.active = active;
	}
}
