package com.yamaha.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.yamaha.entities.User;

import com.yamaha.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	public UserRepository repository;
	

	@Override
	public List<User> showAll() {
		// TODO Auto-generated method stub
		return (List<User>) repository.findAll();
	}

	@Override
	public User insertUser(User b) {
		// TODO Auto-generated method stub
		return repository.save(b);
	}

	@Override
	public void deleteUser(long id) {
		// TODO Auto-generated method stub
		repository.deleteById(id);
	}

	@Override
	public int tranfer(String username1, String username2,double amt) {
		System.out.println(username1);
		User u1=repository.findByusername(username1);
		User u2=repository.findByusername(username2);
		int i=repository.updateAmountByUsername(u1.getAmount()-amt, username1);
		int j=repository.updateAmountByUsername(u2.getAmount()+amt, username2);
		return i+j;
	}

	@Override
	public int credit(String username,double amt) {
		// TODO Auto-generated method stub
		User u=repository.findByusername(username);
		return repository.updateAmountByUsername(u.getAmount()+amt, username);
		//return 0;
	}

	@Override
	public int debit(String username,double amt) {
		User u=repository.findByusername(username);
		return repository.updateAmountByUsername(u.getAmount()-amt, username);
	}

	@Override
	public User viewUser(String username) {
		// TODO Auto-generated method stub
		return repository.findByusername(username);
	}

	@Override
	public void deleteUser(String username) {
		// TODO Auto-generated method stub
		User u=repository.findByusername(username);
		repository.delete(u);
		
	}
	
}
