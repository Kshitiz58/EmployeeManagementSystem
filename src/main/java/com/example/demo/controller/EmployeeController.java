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

@Controller
public class EmployeeController {

	@Autowired
	private EmployeeService empService;
	
	@Autowired 
	private DepartmentService service;
	
	@GetMapping("/employeeAdd")
	public String getEmployee(Model model) {
		
		
		model.addAttribute("deptList", service.getDepartments());
		return "EmployeeAdd";
	}
	
	@PostMapping("/employeeAdd")
	public String postEmployee(@ModelAttribute Employee employee) {

		empService.addEmployee(employee);
		
		return "redirect:/employeeAdd";
	}
	
	@GetMapping("/employeeList")
	public String listEmployee(Model model) {

		model.addAttribute("empList", empService.getAllEmp());
		return "EmployeeList";
	}
	
	@GetMapping("/editEmployee")
	public String editEmployee(@RequestParam long id, Model model) {

		model.addAttribute("employeeModel", empService.getEmployeeByID(id));
		return "EmployeeEdit";
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
