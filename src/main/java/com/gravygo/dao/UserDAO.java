package com.gravygo.dao;

import java.util.List;

import com.gravygo.model.User;

public interface UserDAO 
{
	int addUser(User user);
	List<User> getAllUsers();
	User getUser(String email);
	int updateUser(User u);
	int deleteUser(String email);
}
