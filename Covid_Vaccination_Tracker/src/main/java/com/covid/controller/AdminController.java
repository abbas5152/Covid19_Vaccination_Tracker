package com.covid.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.covid.model.Admin;
import com.covid.repo.AdminDao;

@RestController
public class AdminController {

	@Autowired
	AdminDao adminDao ;
	
	@PostMapping("/admin")
	public Admin saveAdmin(@RequestBody Admin admin) {
		
		return adminDao.save(admin) ;
	}
	
}
