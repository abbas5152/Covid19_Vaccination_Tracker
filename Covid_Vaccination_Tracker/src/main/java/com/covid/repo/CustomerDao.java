package com.covid.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.covid.model.Customer;

@Repository
public interface CustomerDao extends JpaRepository<Customer, Integer>{

	public Customer findByMobileNumber(String mobileno) ;
	
}
