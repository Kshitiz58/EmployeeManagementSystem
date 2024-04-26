package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.model.Employee;
import com.example.demo.service.DepartmentService;
import com.example.demo.service.EmployeeService;
import com.example.demo.utlis.EmployeeExcelView;
import com.example.demo.utlis.EmployeePdfView;

import jakarta.servlet.http.HttpSession;


@Controller
public class EmployeeController {

	@Autowired
	private EmployeeService empService;
	
	@Autowired 
	private DepartmentService service;
	
	@GetMapping("/employee")
	public String getEmployee(Model model, HttpSession session) {
		
//		if(session.getAttribute("validateUser") == null) {
//			return "login";
//		}
//		
		model.addAttribute("deptList", service.getDepartments());
		return "addEmployee";
	}
	
	@PostMapping("/employee")
	public String postEmployee(@ModelAttribute Employee employee, HttpSession session) {
//		if(session.getAttribute("validateUser") == null) {
//			return "login";
//		}
		empService.addEmployee(employee);
		
		return "redirect:/employee";
	}
	
	@GetMapping("/employeeList")
	public String listEmployee(Model model, HttpSession session) {
//		if(session.getAttribute("validateUser")== null){
//			return "login";
//		}
		
		model.addAttribute("empList", empService.getAllEmp());
		return "listEmployee";
	}
	
	@GetMapping("/editEmployee")
	public String editEmployee(@RequestParam long id, Model model, HttpSession session) {
//		if(session.getAttribute("validateUser") == null) {
//			return "login";
//		}
		model.addAttribute("employeeModel", empService.getEmployeeByID(id));
		return "editEmployee";
	}
	
	@PostMapping("/updateEmployee")
	public String updateEmployee(@ModelAttribute Employee employee, HttpSession session) {
//		if(session.getAttribute("validateUser") == null) {
//			return "login";
//		}
		empService.addEmployee(employee);
		return "redirect:/employeeList";
	}
	

	@GetMapping("/deleteEmployee")
	public String deleteEmployee(@RequestParam Long id, HttpSession session) {
//		if(session.getAttribute("validateUser") == null) {
//			return "login";
//		}
		empService.deleteEmployee(id);
		return "redirect:/employeeList";
	}
	
	@GetMapping("/EmployeeExcel")
	public ModelAndView excelView() {
		
		ModelAndView mv = new ModelAndView();
		mv.addObject("elist", empService.getAllEmp());
		mv.setView(new EmployeeExcelView());
		
		return mv;
		
	}
	
	@GetMapping("/EmployeePdf")
	public ModelAndView pdfView() {
		
		ModelAndView mv = new ModelAndView();
		mv.addObject("eList", empService.getAllEmp());
		mv.setView(new EmployeePdfView());
		
		return mv;
	}
}
