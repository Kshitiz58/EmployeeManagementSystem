package com.example.demo.service;

import com.example.demo.model.User;

public interface UserService {

	User userLogin(String un, String psw);

	void userSignup(User user);
	
	User isUserExist(String un);
	
	void resetUser(User user);
}
