package com.example.demo.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.example.demo.model.Department;
import com.example.demo.service.DepartmentService;

@RestController
public class DepartmentRestController {

	@Autowired
	private DepartmentService deptService;
	
	@GetMapping("/api/dept/list")
	public List<Department> geDepartments(){
		return deptService.getDepartments();
	}
	
	@GetMapping("/api/dept/{id}")
	public Department getDepartmentById(@PathVariable int id) {
		return deptService.getDepartmentById(id);
	}
	
	@PostMapping("/api/dept/add")
	public String addDepartment(@RequestBody Department dept) {
		deptService.addDepartment(dept);
		return "Department Added Success!!";
	}
	
	@DeleteMapping("/api/dept/delete/{id}")
	public String deleteDepartment(@PathVariable int id) {
		return deptService.deleteDepartment(id);
	}
	
	@PutMapping("/api/dept/update")
	public String updateeEmployee(@RequestBody Department dept) {
		deptService.updateDepartment(dept);
		return "Department Update Sucsess!!";
	}
	
	@GetMapping("/api/dept/j2o")
	public String JsonToObject(Model model) {
		RestTemplate temp = new RestTemplate();
		Department dept = temp.getForObject("http://localhost:9090/api/emp/2", Department.class);
		return "Department Name : "+dept.getDpt_Name();
	}
	
	@GetMapping("/api/dept/ja20a")
	public String JsonArrayToObject() {
		
		RestTemplate temp = new RestTemplate();
		Department[] depts = temp.getForObject("http://localhost:9090/api/dept/list", Department[].class);
		return	"First Name :"+depts[0].getDpt_Name();
	}
}
