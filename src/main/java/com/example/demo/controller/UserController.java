package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.model.User;
import com.example.demo.service.UserService;

@Controller
public class UserController {

	@Autowired
	private UserService service;
	
	@GetMapping({"/","/login"})
	public String getlogin() {
		return "login";
	}
	
	@PostMapping("/login")
	public String doLogin(@ModelAttribute User user, Model model) {
		User usr = service.userLogin(user.getUsername(), user.getPassword());
		
		if(usr != null) {
			model.addAttribute("uname", user.getUsername());
			return "index";
		}
		model.addAttribute("message","User not Exist!!");
		return "login";
	}
	
	@GetMapping("/signup")
	public String getSignup() {
		
		return "signup";

	}
	
	@PostMapping("/signup")
	public String postSignup(@ModelAttribute User user) {
		
		service.userSignup(user);
		
		return "login";
		
	}
	
}
