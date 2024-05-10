package com.example.demo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private UserRepository userRepo;

	@Override
	public User userLogin(String un, String psw) {
		
		return userRepo.findByUsernameAndPassword(un, psw);
	}

	@Override
	public void userSignup(User user) {
		userRepo.save(user);
		
	}

	@Override
	public User isUserExist(String un) {
		
		return userRepo.findByUsername(un);
	}

	@Override
	public void resetUser(User user) {
		
		userRepo.save(user);
	}
	
	

	
}
