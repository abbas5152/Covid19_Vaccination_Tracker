package com.covid.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.covid.model.AdminLoginSession;

@Repository
public interface AdminLoginSessionDao extends JpaRepository<AdminLoginSession, Integer> {

	public AdminLoginSession findByAdminId(Integer adminId) ;
	
	public AdminLoginSession findByUuid(String key) ;
}
