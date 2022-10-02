package com.covid.service;

import java.time.LocalDate;
import java.util.List;

import com.covid.exception.VaccineInventoryNotFound;
import com.covid.model.Inventory;

public interface VaccineInventoryService {
	
		public Inventory saveInventory(Inventory inv,String key) throws VaccineInventoryNotFound;
	
		public List<Inventory> allVaccineInventory(String key) throws VaccineInventoryNotFound;	
		
		public Inventory getVaccineInventoryById(Integer centerId,String key) throws VaccineInventoryNotFound;
		
		public Inventory updateVaccineInventory(Inventory vaccineInv,String key) throws VaccineInventoryNotFound;
		
		public Boolean deleteVaccineInventory(Integer inventoryId,String key) throws VaccineInventoryNotFound;
		
		public List<Inventory> getVaccineInventoryBydate(LocalDate date,String key)throws VaccineInventoryNotFound;
		
}
