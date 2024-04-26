package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.model.Department;
import com.example.demo.service.DepartmentService;
import com.example.demo.utlis.DepartmentExcelView;
import com.example.demo.utlis.DepartmentPdfView;

@Controller
public class DepartmentController {

	@Autowired
	private DepartmentService deptservice;

	@GetMapping("/department")
	public String getDepartment() {
		return "addDepartment";
	}

	@PostMapping("/department")
	public String postDepartment(@ModelAttribute Department dept) {
		deptservice.addDepartment(dept);
		return "addDepartment";
	}
	
	@GetMapping("/departmentList")
	public String listDepartment(Model model) {
		model.addAttribute("deptList", deptservice.getDepartments());
		return "listDepartment";
	}
	@GetMapping("/editDepartment")
	public String editDepartment(@RequestParam int id, Model model) {
		model.addAttribute("deptModel", deptservice.getDepartmentById(id));
		return "editDepartment";
		
	}
	
	@PostMapping("/updateDepartment")
	public String updateDepartment(@ModelAttribute Department department) {
		deptservice.addDepartment(department);
		return "redirect:/departmentList";
	}
	
	@GetMapping("/deleteDepartment")
	public String deleteDepartment(@RequestParam int id) {
		
		deptservice.deleteDepartment(id);
		return "redirect:/departmentList";
		
	}
	
	@GetMapping("departmentExcel")
	public ModelAndView excelView() {
		
		ModelAndView mv = new ModelAndView();
		mv.addObject("dlist", deptservice.getDepartments());
		mv.setView(new DepartmentExcelView());
		
		return mv;
		
	}
	
	@GetMapping("departmentPdf")
	public ModelAndView pdfView() {
		
		ModelAndView mv = new ModelAndView();
		mv.addObject("dList", deptservice.getDepartments());
		mv.setView(new DepartmentPdfView());
		
		return mv;
	}

}
