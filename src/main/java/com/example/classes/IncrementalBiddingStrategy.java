package com.example.classes;

import com.example.entity.Item;
import com.example.entity.User;
import com.example.interfaces.BiddingStrategy;

public class IncrementalBiddingStrategy implements BiddingStrategy {
    @Override
    public double bid(Item item, User user) {
        double currentBid = item.getCurrentHighestBid();
        double newBid = currentBid + 1.0;
        item.placeBid(user, newBid);
        return newBid;
    }
}