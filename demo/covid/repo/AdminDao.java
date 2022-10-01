package com.covid.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.covid.model.Admin;

@Repository
public interface AdminDao extends JpaRepository<Admin, Integer>{

	public Admin findByMobileNumber(String mobileNo) ;
}
