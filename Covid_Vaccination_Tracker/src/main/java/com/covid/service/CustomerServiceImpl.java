package com.covid.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.covid.exception.AdminException;
import com.covid.exception.CustomerException;
import com.covid.model.Admin;
import com.covid.model.Customer;
import com.covid.repo.CustomerDao;

public class CustomerServiceImpl implements CustomerService {

	@Autowired
	CustomerDao customerDao ;
	
	@Override
	public Customer registerCustomer(Customer customer) {
		return customerDao.save(customer) ;
	}


	

	

	@Override
	public Customer getCustomerById(Integer customerId) throws CustomerException {
		Optional<Customer> customer = customerDao.findById(customerId) ;
		if(customer.isEmpty()) {
			throw new CustomerException("Admin is Not Found with Id "+ customerId);
			
		}
		return customer.get();
	}

	@Override
	public Customer deleteCustomerById(Integer customerId) throws CustomerException {
		Optional<Customer> customer = customerDao.findById(customerId);
		if(customer.isEmpty()) {
			throw new CustomerException("Admin is Not Found with Id "+ customerId);
			
		}
		customerDao.delete(customer.get());
		
		return customer.get();
	}






	@Override
	public Customer updateCustomer(Customer customer) throws CustomerException {
Optional<Customer> customer2 = customerDao.findById(customer.getCustomerId()) ;
		
		if(customer2== null) {
			throw new  CustomerException("Admin is Not Found") ;
		}
		return customerDao.save(customer) ;
	}

	
}
