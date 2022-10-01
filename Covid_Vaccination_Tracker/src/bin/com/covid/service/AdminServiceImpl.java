package com.covid.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.covid.exception.AdminException;
import com.covid.model.Admin;
import com.covid.repo.AdminDao;

public class AdminServiceImpl implements AdminService {

	@Autowired
	AdminDao adminDao ;
	
	@Override
	public Admin registerAdmin(Admin admin) {
		return adminDao.save(admin) ;
	}

	@Override
	public Admin updateAdmin(Admin admin) throws AdminException {
		
		Admin admin2 = adminDao.getById(admin.getAdminId()) ;
		if(admin2== null) {
			throw new  AdminException("Admin is Not Found") ;
		}
		return adminDao.save(admin) ;
		
		
	}

	@Override
	public Admin getAdminById(Integer adminId) throws AdminException {
		
		Optional<Admin> admin = adminDao.findById(adminId) ;
		if(admin.isEmpty()) {
			throw new AdminException("Admin is Not Found with Id "+ adminId);
			
		}
		return admin.get();
	}

	@Override
	public Admin deleteAdminById(Integer adminId) throws AdminException {
		Optional<Admin> admin = adminDao.findById(adminId) ;
		if(admin.isEmpty()) {
			throw new AdminException("Admin is Not Found with Id "+ adminId);
			
		}
		adminDao.delete(admin.get());
		
		return admin.get();
	}

}
