package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.MyUser;


public interface UserRepository extends JpaRepository<MyUser, Integer>{
	
	MyUser findByUsernameAndPassword(String un, String psw);
	
	MyUser findByUsername(String un);
	
	MyUser findByEmail(String email);
	
}
