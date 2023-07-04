package com.example.service;

import java.util.ArrayList;
import java.util.List;

import com.example.entity.User;

public class UserManagementService {

	private List<User> users;

	/**
	 * Constructs a UserManagementService instance. Initializes the list of users.
	 */
	public UserManagementService() {
		this.users = new ArrayList<>();
	}

	/**
	 * Creates a new user with the specified username and password.
	 *
	 * @param username The username for the new user.
	 * @param password The password for the new user.
	 */
	public void createUser(String username, String password) {
		User user = new User(username, password);
		users.add(user);
	}

	/**
	 * Performs user login with the specified username and password.
	 *
	 * @param username The username for the login.
	 * @param password The password for the login.
	 * @return The logged-in user.
	 */
	public User login(String username, String password) {
		User login = new User(username, password);
		users.add(login);
		return login;
	}
}