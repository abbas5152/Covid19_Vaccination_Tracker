package com.covid.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.covid.exception.VaccineInventoryNotFound;
import com.covid.model.Inventory;
import com.covid.model.VaccinationCenter;
import com.covid.model.VaccineCount;
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
	
	@Override
	public List<Inventory> allVaccineInventory() throws VaccineInventoryNotFound {
		
		List<Inventory> list = invDao.findAll();
		
		if(list.size()>0) {
			
			return list;
			
		}
		
		throw new VaccineInventoryNotFound("List empty, need to add Inventory first!");
		
	}

	@Override
	public Inventory getVaccineInventoryById(Integer centerId) throws VaccineInventoryNotFound {
		
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
	public Inventory updateVaccineInventory(Inventory vaccineInv) throws VaccineInventoryNotFound {
		
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
	public Boolean deleteVaccineInventory(Inventory vaccineInv) throws VaccineInventoryNotFound {
		
		Optional<Inventory> opt = invDao.findById(vaccineInv.getInventoryId());
		
		if(opt.isPresent()) {
			
			invDao.deleteById(vaccineInv.getInventoryId());
			
			return true;
			
		}
		
		throw new VaccineInventoryNotFound("Vaccine Inventory Not Found");
		
	}

	@Override
	public List<Inventory> getVaccineInventoryBydate(LocalDate date) throws VaccineInventoryNotFound{
		
		List<Inventory> inventoryList =  invDao.getInventoryByDate(date);
		
		if(inventoryList.size()>0) {
			
			return inventoryList;
			
		}
		
		throw new VaccineInventoryNotFound("No vaccine Found on this date");
	}

}
