package com.covid.service;

import com.covid.exception.AdminException;
import com.covid.exception.CustomerException;
import com.covid.model.Admin;
import com.covid.model.Customer;

public interface CustomerService {

	
	public Customer registerCustomer(Customer customer)  ;
	
	public Customer updateCustomer(Customer customer)throws CustomerException ;
	
	public Customer getCustomerById(Integer customerId)throws CustomerException ;
	
	public Customer deleteCustomerById(Integer customerId)throws CustomerException ;
	
}
