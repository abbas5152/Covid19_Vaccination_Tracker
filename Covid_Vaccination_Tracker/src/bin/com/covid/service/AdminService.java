package com.covid.service;

import com.covid.exception.AdminException;
import com.covid.model.Admin;

public interface AdminService {

	public Admin registerAdmin(Admin admin)  ;
	
	public Admin updateAdmin(Admin admin)throws AdminException ;
	
	public Admin getAdminById(Integer adminId)throws AdminException ;
	
	public Admin deleteAdminById(Integer adminId)throws AdminException ;
	
}
