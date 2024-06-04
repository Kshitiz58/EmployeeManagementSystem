package com.example.demo.service;

import com.example.demo.model.MyUser;

public interface UserService {

	MyUser userLogin(String un, String psw);

	void userSignup(MyUser myUser);
	
	MyUser isUserExist(String un);
	
	void saveUser(MyUser myUser);

}
