package com.example.entity;

public class Bid {

	private Item item;
	private double amount;
	private User Bidder;
	private boolean winningBid;

	// constructor
	public Bid(Item item, User bidder, double amount, boolean winningBid) {
		super();
		this.item = item;
		this.amount = amount;
		Bidder = bidder;
		this.winningBid = winningBid;
	}

	// Getters and setters
	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public User getBidder() {
		return Bidder;
	}

	public void setBidder(User bidder) {
		Bidder = bidder;
	}

	public boolean isWinningBid() {
		return winningBid;
	}

	public void setWinningBid(boolean winningBid) {
		this.winningBid = winningBid;
	}

}
