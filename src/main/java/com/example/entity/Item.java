package com.example.entity;

import java.util.ArrayList;
import java.util.List;

public class Item {

	private String name;
	private String description;
	private double currentHighestBid;
	private User highestBidder;
	private List<Bid> bids;

	// constructor
	public Item(String name, String description, double currentHighestBid) {
		super();
		this.name = name;
		this.description = description;
		this.currentHighestBid = currentHighestBid;
		this.highestBidder = highestBidder;
		this.bids = new ArrayList<>();
	}

	// Getters and setters
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public double getCurrentHighestBid() {
		return currentHighestBid;
	}

	public void setCurrentHighestBid(double currentHighestBid) {
		this.currentHighestBid = currentHighestBid;
	}

	public User getHighestBidder() {
		return highestBidder;
	}

	public void setHighestBidder(User highestBidder) {
		this.highestBidder = highestBidder;
	}

	public List<Bid> getBids() {
		return bids;
	}

	public void setBids(List<Bid> bids) {
		this.bids = bids;
	}
	
	/**
     * Places a bid on the item by the given user with the bid amount.
     * the bid amount is higher than the current highest bid, the highest bidder.
     *
     * @param user      
     * @param bidAmount 
     */

	public void placeBid(User user, double bidAmount) {
		Bid bidding = new Bid(this, user, bidAmount, false);
		bids.add(bidding);
		if (bidAmount > currentHighestBid) {
			currentHighestBid = bidAmount;
			highestBidder = user;
			bidding.setWinningBid(true);
		}

	}

}
