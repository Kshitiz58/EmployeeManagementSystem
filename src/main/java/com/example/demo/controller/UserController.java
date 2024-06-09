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

import com.example.demo.model.MyUser;
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
	public String doLogin(@ModelAttribute MyUser myUser, Model model, HttpSession session,
			@RequestParam("g-recaptcha-response") String recptCode) throws IOException {
		
		if (VerifyRecaptcha.verify(recptCode)) {
			myUser.setPassword(DigestUtils.md5DigestAsHex(myUser.getPassword().getBytes()));
			MyUser usr = service.userLogin(myUser.getUsername(), myUser.getPassword());

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
	public String postSignup(@ModelAttribute MyUser myUser, Model model) {

		//Check confirm password
		
		//Check duplicate user
		if((service.isUserExist(myUser.getUsername()) != null)) {
			logger.info("User already exist in database.");
			model.addAttribute("message", "User already Exist!!, Please select another Username.");
			return "/signup";
		}
		logger.info("User Singnup Success.");
		myUser.setPassword(DigestUtils.md5DigestAsHex(myUser.getPassword().getBytes()));
		service.userSignup(myUser);
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
	
	@GetMapping("/forgetpassword")
	public String getForgetPassword() {
		return "ForgetPassword";
	}
	
	@PostMapping("/forgetpassword")
	public String postForgetPassword(@RequestParam String email, Model model) {
		mailUtil.SendEmail(email);
		model.addAttribute("message","Link forwarded successfully, go to your email.");
		return "ForgetPassword";
	}
	
	@GetMapping("/resetpassword")
	public String resetPassword() {
		return "ResetPassword";
	}
	
	@PostMapping("/resetpassword")
	public String postResetPassword(Model model,@RequestParam String username, 
			@RequestParam String password, @RequestParam String confirmPassword) {
		
		//Check if the password match
		if (!password.equals(confirmPassword)) {
	        model.addAttribute("message", "Password mismatch, type same password.");
	        return "ResetPassword";
	    }
		//Check if the user exist in the database.
		MyUser myUser = service.isUserExist(username);
	    if (myUser == null) {
	    	logger.info("User not found in the database.");
	    	model.addAttribute("message", "User not exist, Please select valid username.");
	        return "ResetPassword";
	    }
	    // Update the password
	    myUser.setPassword(DigestUtils.md5DigestAsHex(password.getBytes()));
	    service.saveUser(myUser);
	    logger.info("Password changed successfully!!");
	    return "redirect:/login";
	}	
	

	
}
