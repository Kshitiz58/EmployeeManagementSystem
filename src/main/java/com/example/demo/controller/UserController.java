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
import com.example.demo.utlis.VerifyRecaptcha;

import jakarta.servlet.http.HttpSession;

@Controller
public class UserController {

	@Autowired
	private UserService service;

	@GetMapping({ "/", "/login" })
	public String getlogin() {
		return "loginForm";
	}

	@PostMapping("/login")
	public String doLogin(@ModelAttribute User user, Model model, HttpSession session,
			@RequestParam("g-recaptcha-response") String recptCode) throws IOException {
		if (VerifyRecaptcha.verify(recptCode)) {
			User usr = service.userLogin(user.getUsername(), user.getPassword());

			if (usr != null) {
				session.setAttribute("validateuser", usr);
				session.setMaxInactiveInterval(200);

//			model.addAttribute("uname", user.getUsername());
				return "index";
			} else {
				model.addAttribute("message", "User not Exist!!");
				return "loginForm";
			}
		}
		model.addAttribute("message","You are Robot!!");
		return "loginForm";

	}

	@GetMapping("/signup")
	public String getSignup() {

		return "signup";

	}

	@PostMapping("/signup")
	public String postSignup(@ModelAttribute User user) {

		service.userSignup(user);

		return "loginForm";

	}
	@GetMapping("/logout")
	public String logoutUser(HttpSession session) {
		//session kill
		session.invalidate();
		return "login";
	}
	
	@GetMapping("/profile")
	public String getPrrofile() {
		return "profile";
	}

	
}
