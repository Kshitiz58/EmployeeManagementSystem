package com.example.demo.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.utlis.MailUtlis;

@Controller
public class ContactController {

	@Autowired
	MailUtlis mailUtil;

	@GetMapping("/contact")
	public String getUpload() {
		return "contact";

	}

	@PostMapping("/contact")
	public String postContact(@RequestParam String toEmail, @RequestParam String subject, @RequestParam String message,
			@RequestParam MultipartFile file) {

		mailUtil.sendEmail(toEmail, subject, message);
		try {
			System.out.println("---------"+file.getResource().getURI().getPath());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "contact";

	}
}
