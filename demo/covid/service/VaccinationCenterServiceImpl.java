package com.covid.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.covid.exception.LoginException;
import com.covid.exception.VaccineCenterException;
import com.covid.exception.VaccineCenterNotFoundException;
import com.covid.model.AdminLoginSession;

import com.covid.model.CustomerLoginSession;
import com.covid.model.VaccinationCenter;
import com.covid.repo.AdminLoginSessionDao;

import com.covid.repo.CustomerLoginSessionDao;

import com.covid.repo.VaccinationCenterDao;

@Service
public class VaccinationCenterServiceImpl implements VaccinationCenterService {

	@Autowired
	private VaccinationCenterDao dao;
	
	@Autowired
	private AdminLoginSessionDao adminLoginSessionDao;
	
	@Autowired
	private CustomerLoginSessionDao customerLoginSessionDao;

	@Override
	public List<VaccinationCenter> allVaccineCenters(String key) {
		

		
		AdminLoginSession adminLoginSession = adminLoginSessionDao.findByUuid(key);
		CustomerLoginSession customerLoginSession = customerLoginSessionDao.findByUuid(key);
		
		if(adminLoginSession == null &&  customerLoginSession ==null) {
			throw new LoginException("Unauthorised Access");
		}
		
		
			
		
		List<VaccinationCenter> list = dao.findAll();
		if (list.size() == 0)
			throw new VaccineCenterException("No Vaccination Center Found...");
		return list;
	}

	@Override
	public VaccinationCenter getVaccineCenter(Integer centerid,String key) {
		AdminLoginSession adminLoginSession = adminLoginSessionDao.findByUuid(key);
		CustomerLoginSession customerLoginSession = customerLoginSessionDao.findByUuid(key);
		
		if(adminLoginSession == null &&  customerLoginSession ==null) {
			throw new LoginException("Unauthorised Access");
		}
		
			
	
		return dao.findById(centerid).orElseThrow(
				() -> new VaccineCenterNotFoundException("No vaccination center is found by the id : " + centerid));
	}

	@Override
	public VaccinationCenter addVaccineCenter(VaccinationCenter center,String key) {

		AdminLoginSession adminLoginSession = adminLoginSessionDao.findByUuid(key);
			
		if(adminLoginSession == null ) {
			throw new LoginException("Unauthorised Access");
		}
		
	
		
		Optional<VaccinationCenter> vc = dao.findById(center.getCenterId());

		if (vc.isPresent()) {
			throw new VaccineCenterException("Vaccination center is present with the same Id");
		}
		return dao.save(center);
	}

	@Override
	public VaccinationCenter updateVaccineCenter(VaccinationCenter center,String key) {
		AdminLoginSession adminLoginSession = adminLoginSessionDao.findByUuid(key);
		
		if(adminLoginSession == null ) {
			throw new LoginException("Unauthorised Access");
		}
		
	
		Optional<VaccinationCenter> vc = dao.findById(center.getCenterId());

		if (vc.isPresent()) {
			return dao.save(center);
		} else
			throw new VaccineCenterNotFoundException("Vaccination center is not present with the same Id");

	}

	@Override
	public boolean deleteVaccineCenter(VaccinationCenter center,String key) {
AdminLoginSession adminLoginSession = adminLoginSessionDao.findByUuid(key);
		
		if(adminLoginSession == null ) {
			throw new LoginException("Unauthorised Access");
		}
		
	
		Optional<VaccinationCenter> vc = dao.findById(center.getCenterId());

		if (vc.isPresent()) {
			dao.delete(center);
			return true;
		} else
			throw new VaccineCenterNotFoundException("Vaccination center is not present with the same Id");
	}

}
