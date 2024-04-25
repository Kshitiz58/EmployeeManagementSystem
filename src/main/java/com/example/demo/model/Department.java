package com.example.demo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "department_tbl")
public class Department {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String dpt_Name;
	private String dpt_Head;
	private String dpt_Phone;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getDpt_Name() {
		return dpt_Name;
	}
	public void setDpt_Name(String dpt_Name) {
		this.dpt_Name = dpt_Name;
	}
	public String getDpt_Head() {
		return dpt_Head;
	}
	public void setDpt_Head(String dpt_Head) {
		this.dpt_Head = dpt_Head;
	}
	public String getDpt_Phone() {
		return dpt_Phone;
	}
	public void setDpt_Phone(String dpt_Phone) {
		this.dpt_Phone = dpt_Phone;
	}
	
	
}
