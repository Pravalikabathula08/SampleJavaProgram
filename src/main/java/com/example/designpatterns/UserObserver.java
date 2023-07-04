package com.example.designpatterns;

import com.example.entity.Item;
import com.example.entity.User;
import com.example.interfaces.Observer;

public class UserObserver implements Observer {
	private User user;

	public UserObserver(User user) {
		this.user = user;
	}

	@Override
	public void update(Item item) {
	}
}