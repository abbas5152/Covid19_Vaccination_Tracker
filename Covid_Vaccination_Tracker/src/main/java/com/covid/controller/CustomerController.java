package com.covid.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.covid.model.Customer;
import com.covid.repo.CustomerDao;

@RestController
public class CustomerController {

	@Autowired
	CustomerDao customerDao ;
	
	@PostMapping("customer")
	public Customer saveCustomer(@RequestBody Customer customer) {
		return customerDao.save(customer) ;
	}
}
