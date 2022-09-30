package com.covid.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.covid.model.AdminDTO;
import com.covid.model.CustomerDTO;
import com.covid.service.LoginServiceImpl;

@RestController
public class LoginController {

	@Autowired 
	LoginServiceImpl loginService ;
	
	@PostMapping("/adminLogin")
	public ResponseEntity<String> adminLogging(@RequestBody AdminDTO adminDto){
		String str =  loginService.adminLoggin(adminDto) ;
		
		return new ResponseEntity<String>(str, HttpStatus.ACCEPTED) ;
	}
	
	@DeleteMapping("/adminLogin/{key}")
	public ResponseEntity<String> adminLoggedOut(@PathVariable("key") String key){
		
		String str = loginService.adminLoggedOut(key) ;
		
		return new ResponseEntity<>(str , HttpStatus.OK) ;
	}
	
	@PostMapping("/customerLogin")
	public ResponseEntity<String> customerLogging(@RequestBody CustomerDTO cusstomerDto){
		String str =  loginService.CustomerLoggin(cusstomerDto);
		
		return new ResponseEntity<String>(str, HttpStatus.ACCEPTED) ;
	}
	
	@DeleteMapping("/customerLogin/{key}")
	public ResponseEntity<String> customerLoggedOut(@PathVariable("key") String key){
		
		String str = loginService.customerLoggedOut(key);
		
		return new ResponseEntity<>(str , HttpStatus.OK) ;
	}
}
