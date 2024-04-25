package com.example.demo.service;

import java.util.List;

import com.example.demo.model.Department;

public interface DepartmentService {

	void addDepartment(Department department);

	List<Department> getDepartments();

	Department getDepartmentById(int id);
	
	void deleteDepartment(int id);
	
	void updateDepartment(Department department);
	

}
