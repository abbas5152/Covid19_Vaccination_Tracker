package com.covid.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.covid.model.CustomerLoginSession;

@Repository
public interface CustomerLoginSessionDao extends JpaRepository<CustomerLoginSession, Integer>{

	public CustomerLoginSession findByCustomerId(Integer customerId) ;
	
	public CustomerLoginSession findByUuid(String key) ;
}
