package com.rgt.test.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rgt.test.Service.UserService;
import com.rgt.test.entity.User;

@RestController
@RequestMapping("/api/users")
public class UserController {

	@Autowired
	private UserService userService;

	@PostMapping
	public User createUser(@RequestBody User user) throws IOException {
		return userService.createUser(user);
	}

	@PutMapping("/{id}")
	public User updateUser(@PathVariable long id, @RequestBody User updatedUser) throws IOException, ClassNotFoundException {
		updatedUser.setId(id);
		return userService.updateUser(id, updatedUser);
	}
	
	@GetMapping("/{id}")
	public User getUser(@PathVariable long id) throws IOException, ClassNotFoundException {
		return userService.getUser(id);
	}

	@GetMapping
	public List<User> getAllUsers() throws IOException, ClassNotFoundException {
		return userService.getAllUsers();
	}
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteUser(@PathVariable long id) throws IOException {
	    userService.deleteUser(id);
	    return ResponseEntity.ok("User deleted successfully");
	}
}