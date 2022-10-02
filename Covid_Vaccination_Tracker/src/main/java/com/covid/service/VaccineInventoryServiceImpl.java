package com.covid.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.covid.exception.LoginException;
import com.covid.exception.VaccineInventoryNotFound;
import com.covid.model.AdminLoginSession;
import com.covid.model.Inventory;
import com.covid.model.VaccinationCenter;
import com.covid.model.VaccineCount;
import com.covid.repo.AdminLoginSessionDao;
import com.covid.repo.InventoryDao;
import com.covid.repo.VaccinationCenterDao;
import com.covid.repo.VaccineCountDao;

@Service
public class VaccineInventoryServiceImpl implements VaccineInventoryService{
	
	@Autowired
	private InventoryDao invDao;
	
	@Autowired
	private VaccinationCenterDao vccDao;
	
	@Autowired
	private VaccineCountDao vcDao;
	
	@Autowired
	AdminLoginSessionDao daoAdminLSes;
	
	@Override
	public Inventory saveInventory(Inventory inv,String key) throws VaccineInventoryNotFound {
		
		    AdminLoginSession optAdminses = daoAdminLSes.findByUuid(key);
		      
		      if(optAdminses==null)
		      {
		    	   throw new LoginException("Unathrosied access denied..");
		      }
		
//		List<VaccinationCenter> vaccineCenterList = inv.getListvaccinationCenter();
//		
//		for (VaccinationCenter vaccinationCenter : vaccineCenterList) {
//			
//			vaccinationCenter.setInventory(inv);
//			
//		}
//		
//		List<VaccineCount> vaccinecountList = inv.getVaccinecountList();
//		
//		for (VaccineCount vaccineCount : vaccinecountList) {
//			
//			vaccineCount.setInventory(inv);
//		
//		}
		
		return invDao.save(inv);
		
	}
	
	@Override
	public List<Inventory> allVaccineInventory(String key) throws VaccineInventoryNotFound {
		
		List<Inventory> list = invDao.findAll();
		
		if(list.size()>0) {
			
			return list;
			
		}
		
		throw new VaccineInventoryNotFound("List empty, need to add Inventory first!");
		
	}

	@Override
	public Inventory getVaccineInventoryById(Integer centerId,String key) throws VaccineInventoryNotFound {
		
		AdminLoginSession optAdminses = daoAdminLSes.findByUuid(key);
	      
	      if(optAdminses==null)
	      {
	    	   throw new LoginException("Unathrosied access denied..");
	      }
		
		
		Optional<VaccinationCenter> opt = vccDao.findById(centerId);
		
		if(opt.isPresent()) {
			
			if(opt.get().getInventory()!=null) {
				
				return opt.get().getInventory();
				
			}else {
				
				throw new VaccineInventoryNotFound("Please Add some Inventory");
				
			}
			
		}
		
		throw new VaccineInventoryNotFound("Please enter valid center Id");
		
	}

	@Override
	public Inventory updateVaccineInventory(Inventory vaccineInv,String key) throws VaccineInventoryNotFound {
		
		AdminLoginSession optAdminses = daoAdminLSes.findByUuid(key);
	      
	      if(optAdminses==null)
	      {
	    	   throw new LoginException("Unathrosied access denied..");
	      }
		
		
		Optional<Inventory> opt = invDao.findById(vaccineInv.getInventoryId());
		
		if(opt.isPresent()) {
			
			List<VaccinationCenter> centerList = opt.get().getListvaccinationCenter();
			
			for (VaccinationCenter vaccinationCenter : centerList) {
				
				vaccinationCenter.setInventory(vaccineInv);
				
				vccDao.save(vaccinationCenter);
				
			}
			
			List<VaccineCount> vaccineCounts = opt.get().getVaccinecountList();
			
			for (VaccineCount vaccineCount : vaccineCounts) {
				
				vaccineCount.setInventory(vaccineInv);
				
				vcDao.save(vaccineCount);
				
			}
			
			return invDao.save(vaccineInv);
			
		}
		
		throw new VaccineInventoryNotFound("Vaccine Inventory not found!");
	}

	@Override
	public Boolean deleteVaccineInventory(Integer inventoryId,String key) throws VaccineInventoryNotFound {
		
		AdminLoginSession optAdminses = daoAdminLSes.findByUuid(key);
	      
	      if(optAdminses==null)
	      {
	    	   throw new LoginException("Unathrosied access denied..");
	      }
		
		Optional<Inventory> opt = invDao.findById(inventoryId);
		
		if(opt.isPresent()) {
			
			invDao.deleteById(inventoryId);
			
			return true;
			
		}
		
		throw new VaccineInventoryNotFound("Vaccine Inventory Not Found");
		
	}

	@Override
	public List<Inventory> getVaccineInventoryBydate(LocalDate date,String key) throws VaccineInventoryNotFound{
		
		AdminLoginSession optAdminses = daoAdminLSes.findByUuid(key);
	      
	      if(optAdminses==null)
	      {
	    	   throw new LoginException("Unathrosied access denied..");
	      }
		
		List<Inventory> inventoryList =  invDao.getInventoryByDate(date);
		
		if(inventoryList.size()>0) {
			
			return inventoryList;
			
		}
		
		throw new VaccineInventoryNotFound("No vaccine Found on this date");
	}

	

}
