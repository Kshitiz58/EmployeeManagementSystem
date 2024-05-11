package com.example.demo.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class GalleryController {

	@GetMapping("/gallery")
	public String getGallery(Model model) {
		String[] imgNameList = new File("src/main/resources/static/image").list();
		model.addAttribute("imgList", imgNameList);
		return "gallery";
	}
	
	@GetMapping("/upload")
	public String getUpload() {
		
		return "Upload";
		
	}
	
	@PostMapping("/upload")
	public String postUpload(@RequestParam MultipartFile image, Model model) throws IOException{
		
		if(!image.isEmpty()) {
			Files.copy(image.getInputStream(), Path.of("src/main/resources/static/image/"+image.getOriginalFilename()), StandardCopyOption.REPLACE_EXISTING);
			 model.addAttribute("message", "Image Upload Success!!");
			 return "upload";
		}
		model.addAttribute("message","Image Upload Failed!!");
		return "redirect:/upload";
	}

}
