package com.userservice.service;

import java.util.List;

import com.userservice.entities.User;


public interface UserService {

	User saveUser(User user);
	List<User> getAllUser();
	User getUserById(String userId);
	String deleteUserById(String id);
	String deleteAllUser();
	User updateUserById(User user,String id);
	
}
