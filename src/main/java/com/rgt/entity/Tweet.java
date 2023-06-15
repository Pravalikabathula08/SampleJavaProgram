package com.rgt.entity;

import java.util.ArrayList;
import java.util.List;

public class Tweet {
    private static int idCounter = 1;

    private int id;
    private String content;
    private String author;
    private String timestamp;
    private List<Tweet> replies;


    public Tweet(String content, String author, String timestamp) {
        this.id = idCounter++;
        this.content = content;
        this.author = author;
        this.timestamp = timestamp;
        this.replies = new ArrayList<Tweet>();
    }

    // Getters

    public Tweet(String content, String author) {
    	this.content = content;
        this.author = author;
	}

	public int getId() {
        return id;
    }

    public String getContent() {
        return content;
    }

    public String getAuthor() {
        return author;
    }

    public String getTimestamp() {
        return timestamp;
    }

    // Methods

    public void like() {
        System.out.println("Tweet liked by " + author);
    }

    public void retweet() {
        System.out.println("Tweet retweeted by " + author);
    }

    public void reply(String content, String author) {
    	 Tweet reply = new Tweet(content, author);
         replies.add(reply);
    }

	public void setId(int id) {
		this.id = id;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}


}

