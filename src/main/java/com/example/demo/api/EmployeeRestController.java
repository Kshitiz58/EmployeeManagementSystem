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

import com.example.demo.model.Employee;
import com.example.demo.service.EmployeeService;

@RestController
public class EmployeeRestController {
	
	@Autowired
	private EmployeeService empService;
	
	@GetMapping("/api/emp/list")
	public List<Employee> getEmployee(){
		return empService.getAllEmp();
	}
	
	@GetMapping("/api/emp/{id}")
	public Employee getEmployeeById(@PathVariable Long id) {
		return empService.getEmployeeByID(id);
	}
	
	@PostMapping("/api/emp/add")
	public String addEmployee(@RequestBody Employee emp) {
		empService.addEmployee(emp);
		return "Employee Added Success!!";
	}

	@PutMapping("/api/emp/update")
	public String updateEmployee(@RequestBody Employee emp) {
		empService.updateEmployee(emp);
		return "Employee Update Success!!";
	}
	
	@DeleteMapping("/api/emp/delete/{id}")
	public String deleteEmployee(@PathVariable Long id) {
		return empService.deleteEmployee(id);
	}
	
	@GetMapping("/api/emp/j2o")
	public String JsonToOnject(Model model) {
		RestTemplate temp = new RestTemplate();
		Employee e = temp.getForObject("http://localhost:9090/api/emp/2", Employee.class);
		return "FirstName ="+e.getFname();
	}
	
	@GetMapping("/api/emp/ja2oa")
	public String JsonArrayToObjectArray() {
		
		RestTemplate rt = new RestTemplate();
		Employee[] emps = rt.getForObject("http://localhost:9090/api/emp/list", Employee[].class);
				return "FirstName = "+emps[0].getFname();
	}
	
	
}
