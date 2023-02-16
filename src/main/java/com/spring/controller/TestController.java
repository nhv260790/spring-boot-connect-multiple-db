package com.spring.controller;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.model1.Person;
import com.spring.model2.Address;
import com.spring.repository1.PersonRepository;
import com.spring.repository2.AddressRepository;

@RestController
public class TestController {
	
	@Autowired
	private PersonRepository personRepository;
	
	@Autowired
	private AddressRepository addressRepository;
	
	@GetMapping("/addData")
	public String addData() {
		personRepository.save(new Person("person1"));
		addressRepository.save(new Address("hcm"));
		return "Data added successfully!";
	}
	
	@GetMapping("/getData")
	public Map<String, Object> getData(){
		List<Person> personList = personRepository.findAll();
		List<Address> addressList = addressRepository.findAll();
		Map<String, Object> map = new HashMap<>();
		map.put("Person", personList);
		map.put("Address", addressList);
		return map;
	} 
	
}
