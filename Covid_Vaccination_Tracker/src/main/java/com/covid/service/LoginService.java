package com.covid.service;

import org.springframework.stereotype.Service;

import com.covid.model.AdminDTO;
import com.covid.model.CustomerDTO;

@Service
public interface LoginService {

	public String adminLoggin(AdminDTO adminDto) ;
	
	public String adminLoggedOut(String key) ;
	
	public String CustomerLoggin(CustomerDTO customerDto) ;
	
	public String customerLoggedOut(String key) ;
}
