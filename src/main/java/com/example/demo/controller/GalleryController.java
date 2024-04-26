package com.example.demo.controller;

import java.io.File;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import jakarta.servlet.http.HttpSession;

@Controller
public class GalleryController {

	@GetMapping("/gallery")
	public String getGallery(Model model, HttpSession session) {

//		if (session.getAttribute("validateUser") == null) {
//			return "login";
//		}

		String[] imgNameList = new File("src/main/resources/static/image").list();
		model.addAttribute("imgList", imgNameList);
		return "gallery";
	}

}
