package com.example.demo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.example.demo.model.Department;
import com.example.demo.repository.DepartmentRepository;
import com.example.demo.service.DepartmentService;

@Service
public class DepartmentServiceImpl implements DepartmentService{

	@Autowired
	private DepartmentRepository deptRepo;
	
	@Override
	public void addDepartment(Department department) {
		deptRepo.save(department);
		
	}

	@Override
	public List<Department> getDepartments() {
		
		return deptRepo.findAll();
	}

	@Override
	public Department getDepartmentById(int id) {
		
		return deptRepo.findById(id).get();
	}

	@Override
	public void deleteDepartment(int id) {
			deptRepo.deleteById(id);
	}

	@Override
	public void updateDepartment(Department department) {
		deptRepo.save(department);
		
	}


	

}
