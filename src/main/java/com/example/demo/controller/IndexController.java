package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.model.Subscriber;
import com.example.demo.service.SubscriberService;

@Controller
public class IndexController {

	@Autowired 
	private SubscriberService subService;
	
	@GetMapping({"/","/index"})
	public String getIndex() {
		return "index1";
	}
	@PostMapping({"/","/index"})
	public String subsribe(@ModelAttribute Subscriber subscribe) {
		subService.subsriberEmail(subscribe);
		return "redirect:/index";
		
	}
	
}
