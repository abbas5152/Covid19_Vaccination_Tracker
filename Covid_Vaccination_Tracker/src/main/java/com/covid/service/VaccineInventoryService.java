package com.covid.service;

import java.time.LocalDate;
import java.util.List;

import com.covid.exception.VaccineInventoryNotFound;
import com.covid.model.Inventory;

public interface VaccineInventoryService {
	
		public Inventory saveInventory(Inventory inv) throws VaccineInventoryNotFound;
	
		public List<Inventory> allVaccineInventory() throws VaccineInventoryNotFound;	
		
		public Inventory getVaccineInventoryById(Integer centerId) throws VaccineInventoryNotFound;
		
		public Inventory updateVaccineInventory(Inventory vaccineInv) throws VaccineInventoryNotFound;
		
		public Boolean deleteVaccineInventory(Integer inventoryId) throws VaccineInventoryNotFound;
		
		public List<Inventory> getVaccineInventoryBydate(LocalDate date)throws VaccineInventoryNotFound;
		
}
