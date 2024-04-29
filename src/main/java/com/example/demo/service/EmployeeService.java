package com.example.demo.service;

import java.util.List;

import com.example.demo.model.Employee;

public interface EmployeeService {

	void addEmployee(Employee employee);
	
	List<Employee> getAllEmp();
	
	Employee getEmployeeByID(Long id);
	
	void updateEmployee(Employee employee);
	
	String deleteEmployee(Long id);
	
}
