package com.rgt.library.entity;

public class Magazine extends Resource {
	private String issueDate;

	public Magazine(String title, String author, String issueDate) {
		super(title, author, "Magazine");
		this.issueDate = issueDate;
	}

	public String getIssueDate() {
		return issueDate;
	}

	public void setIssueDate(String issueDate) {
		this.issueDate = issueDate;
	}

	@Override
	public String toString() {
		return getTitle() + "," + getAuthor() + "," + getType() + "," + getIssueDate();
	}
}
