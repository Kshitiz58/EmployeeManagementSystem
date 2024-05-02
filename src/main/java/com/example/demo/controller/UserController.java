package com.example.demo.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.model.User;
import com.example.demo.service.UserService;
//import com.example.demo.utlis.VerifyRecaptcha;

import jakarta.servlet.http.HttpSession;


@Controller
public class UserController {

	@Autowired
	private UserService service;

	@GetMapping({ "/", "/login" })
	public String getlogin() {
		return "login";
	}

	@PostMapping("/login")
	public String doLogin(@ModelAttribute User user, Model model, HttpSession session,
			@RequestParam("g-recaptcha-response") String recptCode) throws IOException {
//		if (VerifyRecaptcha.verify(recptCode)) {
			User usr = service.userLogin(user.getUsername(), user.getPassword());

			if (usr != null) {
//				session.setAttribute("validateUser", usr);
//				session.setMaxInactiveInterval(300);

//			model.addAttribute("uname", user.getUsername());
				return "index";
			} else {
				model.addAttribute("message", "User not Exist!!");
				return "login";
			}
//		}
//		model.addAttribute("message","You are Robot!!");
//		return "login";

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
	@GetMapping("/logout")
	public String logoutUser(HttpSession session) {
		//session kill
		session.invalidate();
		return "login";
	}
	
	@GetMapping("/profile")
	public String getProfile(HttpSession session) {
//		if(session.getAttribute("validateUser") == null) {
//			return "login";
//		}
		return "profile";
	}

	
}
