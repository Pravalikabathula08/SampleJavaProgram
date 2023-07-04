package com.example.classes;

import com.example.entity.Item;
import com.example.entity.User;
import com.example.interfaces.BiddingStrategy;

public class AutomaticBiddingStrategy implements BiddingStrategy {

	private double maxiBidAmount;

	public AutomaticBiddingStrategy(double maxiBidAmount) {
		super();
		this.maxiBidAmount = maxiBidAmount;
	}

	@Override
	public double bid(Item item, User user) {
		double currentBid = item.getCurrentHighestBid();
		double bidAmounts = currentBid + 1;

		if (bidAmounts > maxiBidAmount) {
			System.out.println("your bid amount exceeds your maximum bid amount..");
			return currentBid;
		}
		item.setCurrentHighestBid(currentBid);
		item.setHighestBidder(user);

		return bidAmounts;
	}
}
