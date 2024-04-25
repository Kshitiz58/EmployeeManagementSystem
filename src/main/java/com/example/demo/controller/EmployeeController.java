package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.model.Employee;
import com.example.demo.service.DepartmentService;
import com.example.demo.service.EmployeeService;


@Controller
public class EmployeeController {

	@Autowired
	private EmployeeService empService;
	
	@Autowired 
	private DepartmentService service;
	
	@GetMapping("/employee")
	public String getEmployee(Model model) {
		
		model.addAttribute("deptList", service.getDepartments());
		return "addEmployee";
	}
	
	@PostMapping("/employee")
	public String postEmployee(@ModelAttribute Employee employee) {
		
		empService.addEmployee(employee);
		
		return "redirect:/employee";
	}
	
	@GetMapping("/employeeList")
	public String listEmployee(Model model) {
		model.addAttribute("empList", empService.getAllEmp());
		return "listEmployee";
	}
	
	@GetMapping("/editEmployee")
	public String editEmployee(@RequestParam long id, Model model) {
		model.addAttribute("employeeModel", empService.getEmployeeByID(id));
		return "editEmployee";
	}
	
	@PostMapping("/updateEmployee")
	public String updateEmployee(@ModelAttribute Employee employee) {
		empService.addEmployee(employee);
		return "redirect:/employeeList";
	}
	

	@GetMapping("/deleteEmployee")
	public String deleteEmployee(@RequestParam Long id) {
		empService.deleteEmployee(id);
		return "redirect:/employeeList";
	}
}
