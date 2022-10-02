package com.covid.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.covid.exception.AdminException;
import com.covid.model.Admin;
import com.covid.repo.AdminDao;

@Service
public class AdminServiceImpl implements AdminService {

	@Autowired
	AdminDao adminDao ;
	
	@Override
	public Admin registerAdmin(Admin admin) {
		
		
		return adminDao.save(admin) ;
		
		
	}

	@Override
	public Admin updateAdmin(Admin admin) throws AdminException {
		
		Optional<Admin> admin2 = adminDao.findById(admin.getAdminId()) ;
		if(admin2.isEmpty()) {
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
