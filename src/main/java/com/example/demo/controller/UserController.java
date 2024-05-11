package com.example.demo.controller;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.model.User;
import com.example.demo.service.UserService;
import com.example.demo.utlis.MailUtlis;
import com.example.demo.utlis.VerifyRecaptcha;

import jakarta.servlet.http.HttpSession;


@Controller
public class UserController {
	
	@Autowired
	private MailUtlis mailUtil;

	@Autowired
	private UserService service;
	
	private static final Logger logger = LoggerFactory.getLogger(UserController.class);

	@GetMapping("/login")
	public String getlogin() {
		return "login";
	}

	@PostMapping("/login")
	public String doLogin(@ModelAttribute User user, Model model, HttpSession session,
			@RequestParam("g-recaptcha-response") String recptCode) throws IOException {
		
		if (VerifyRecaptcha.verify(recptCode)) {
			user.setPassword(DigestUtils.md5DigestAsHex(user.getPassword().getBytes()));
			User usr = service.userLogin(user.getUsername(), user.getPassword());

			if (usr != null) {
				logger.info("User Login Successful");
				session.setAttribute("validateUser", usr);
//				session.setMaxInactiveInterval(300);

//			model.addAttribute("uname", user.getUsername());
				return "redirect:/employeeList";
			} else {
				logger.info("User Login Failed.");
				model.addAttribute("message", "User not Exist!!");
				return "login";
			}
		}
		logger.info("User Login Failed.");
		model.addAttribute("message","You are Robot!!");
		return "login";

	}

	@GetMapping("/signup")
	public String getSignup() {

		return "signup";

	}

	@PostMapping("/signup")
	public String postSignup(@ModelAttribute User user, Model model) {

		//Check confirm password
		
		//Check duplicate user
		if((service.isUserExist(user.getUsername()) != null)) {
			logger.info("User already exist in database.");
			model.addAttribute("message", "User already Exist!!, Please select another Username.");
			return "signup";
		}
		logger.info("User Singnup Success.");
		user.setPassword(DigestUtils.md5DigestAsHex(user.getPassword().getBytes()));
		
		service.userSignup(user);
		logger.info("User Singnup Success.");
		return "redirect:/login";

	}
	@GetMapping("/logout")
	public String logoutUser(HttpSession session) {
		logger.info("Logout Success");
		//session kill
		session.invalidate();
		return "login";
	}
	
	@GetMapping("/profile")
	public String getProfile(HttpSession session) {
		if(session.getAttribute("validateUser") == null) {
			return "login";
		}
		return "Profile";
	}
	
	@GetMapping("/forgetpass")
	public String getForgetPassword() {
		return "ForgetPassword";
	}
	
	@PostMapping("/forgetpass")
	public String postForgetPassword(@RequestParam String mail) {
		
		mailUtil.SendEmail(mail);
		
		return "redirect:/forgetpass";
	}
	
	@GetMapping("/resetpass")
	public String resetPassword() {
		return "";
	}
	
	@PostMapping("/resetpass")
	public String psotResetPassword() {
		
		return "";
	}
	
	

	
}
