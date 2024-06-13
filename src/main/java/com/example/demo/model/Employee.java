package com.example.demo.model;

import java.time.LocalDate;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.CascadeType;
import jakarta.persistence.CollectionTable;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name="employee_tbl")
public class Employee {

	@Id
	@GeneratedValue
	private Long id;
	private String fname;
	private String lname;
	private String gender;
	private String phone;
	private String email;
	
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private LocalDate dob;
	private String company;
	private String post;
	private int salary;
	
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private LocalDate joinDate;

	@ManyToMany
	private List<Department> department;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="address_Id")
	private Address address;
	
	@ElementCollection
	@CollectionTable
	private List<String> project;

}
