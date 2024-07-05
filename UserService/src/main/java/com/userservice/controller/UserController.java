package com.userservice.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.userservice.entities.User;
import com.userservice.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {
	@Autowired
	UserService userService;

	@PostMapping("/")
	public ResponseEntity<User> createUser(@RequestBody User user) {
		user.setUserId(UUID.randomUUID().toString());
		User user1 = userService.saveUser(user);
		return ResponseEntity.status(HttpStatus.CREATED).body(user);
	}

	@GetMapping("/getUser/{id}")
	public ResponseEntity<User> getSingleUser(@PathVariable("id") String id) {
		User userById = userService.getUserById(id);
		return ResponseEntity.status(HttpStatus.OK).body(userById);
	}

	@GetMapping("/getAllUser")
	public ResponseEntity<List<User>> getAllUser() {
		List<User> userById = userService.getAllUser();
		return ResponseEntity.status(HttpStatus.OK).body(userById);
	}
	
	
}
