package com.yamaha.service;

import java.util.List;

import com.yamaha.entities.User;

public interface UserService {
	public List<User> showAll();
	public User insertUser(User b);
	public void deleteUser(long id);
	
	public int tranfer(String username1,String username2, double amt);
	public int credit(String username, double amt);
	public int debit(String username, double amt);
	public User viewUser(String username);
	public void deleteUser(String username);
}
