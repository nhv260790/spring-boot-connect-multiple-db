package com.spring.model2;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "Address")
public class Address {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String addres;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return addres;
	}
	public void setName(String name) {
		this.addres = name;
	}
	public Address(int id, String name) {
		this.id = id;
		this.addres = name;
	}
	public Address(String name) {
		this.addres = name;
	}
	public Address() {
	}
	
	
}
