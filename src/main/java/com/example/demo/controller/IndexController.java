package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import jakarta.servlet.http.HttpSession;

@Controller
public class IndexController {

	@GetMapping("/index")
	public String getHome(HttpSession session) {
		if(session.getAttribute("validateUser") == null) {
			return "login";
		}
		return "index";
	}
}
