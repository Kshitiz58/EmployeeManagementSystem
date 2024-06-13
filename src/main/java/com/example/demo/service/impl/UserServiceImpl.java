package com.example.demo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.MyUser;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepo;

	@Override
	public MyUser userLogin(String un, String psw) {

		return userRepo.findByUsernameAndPassword(un, psw);
	}

	@Override
	public void userSignup(MyUser myUser) {
		userRepo.save(myUser);

	}

	@Override
	public MyUser isUserExist(String un) {

		return userRepo.findByUsername(un);
	}

	@Override
	public void saveUser(MyUser myUser) {
		
		userRepo.save(myUser);

	}

	@Override
	public MyUser searchEmail(String email) {

		return userRepo.findByEmail(email);
	}

}
