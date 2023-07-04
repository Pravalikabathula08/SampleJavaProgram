package com.example.service;

import com.example.entity.Item;
import com.example.entity.User;

public class NotificationService {

	public void notifyUser(User user, Item item) {
		System.out.println("You have been outbid on the item: " + item.getName());

	}
}
