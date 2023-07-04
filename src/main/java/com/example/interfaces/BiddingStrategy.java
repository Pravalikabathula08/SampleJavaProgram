package com.example.interfaces;

import com.example.entity.Item;
import com.example.entity.User;

public interface BiddingStrategy {

	double bid(Item item, User user);

}
