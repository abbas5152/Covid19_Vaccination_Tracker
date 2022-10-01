package com.covid.controller;

import java.time.LocalDate;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.covid.exception.VaccineInventoryNotFound;
import com.covid.model.Admin;
import com.covid.model.Inventory;
import com.covid.repo.AdminDao;
import com.covid.service.VaccineInventoryService;

@RestController
@RequestMapping("/admin/")
public class AdminController {

	@Autowired
	AdminDao adminDao ;
	
	@Autowired
	VaccineInventoryService vaccineInventoryService;
	
	@PostMapping("/")
	public Admin saveAdmin(@RequestBody Admin admin) {
		
		return adminDao.save(admin) ;
		
	}
	
	
	@PostMapping("/addInventory/")
	public ResponseEntity<Inventory> saveInventory(@RequestBody Inventory inv) throws VaccineInventoryNotFound {
		
		Inventory inventory = vaccineInventoryService.saveInventory(inv);
		
		return new ResponseEntity<Inventory>(inventory,HttpStatus.ACCEPTED);
		
	}
	
	@GetMapping("/allInventory/")
	public ResponseEntity<List<Inventory>> getAlInventory() throws VaccineInventoryNotFound {
		
		 List<Inventory> inventoryList =  vaccineInventoryService.allVaccineInventory();
		
		return new ResponseEntity<>(inventoryList,HttpStatus.ACCEPTED);
		
	}
	
	
	@GetMapping("/getInventoryById/{id}")
	public ResponseEntity<Inventory> getInventoryById(@PathVariable("id") Integer id) throws VaccineInventoryNotFound{
		
		Inventory inventory = vaccineInventoryService.getVaccineInventoryById(id);
		
		return new ResponseEntity<Inventory>(inventory,HttpStatus.ACCEPTED);
		
	}

	
	@PostMapping("/updateInventory/")
	public ResponseEntity<Inventory> updateVaccineInventory(@RequestBody Inventory inv) throws VaccineInventoryNotFound{
		
		Inventory inventory = vaccineInventoryService.updateVaccineInventory(inv);
		
		return new ResponseEntity<Inventory>(inventory,HttpStatus.ACCEPTED);
		
	}
	
	
	@DeleteMapping("/deleteInventoryByID/{id}")
	public ResponseEntity<Boolean> deletVaccineInventoryByID(@PathVariable("id") Integer id) throws VaccineInventoryNotFound{
		
		Boolean ans =  vaccineInventoryService.deleteVaccineInventory(id);
		
		return new ResponseEntity<Boolean>(ans,HttpStatus.ACCEPTED);
		
	}
	
	
	@GetMapping("/getByDate/{date}")
	public ResponseEntity<List<Inventory>> getInventoryByDate(@PathVariable("date") @DateTimeFormat(pattern = "dd-MM-yyyy") LocalDate date) throws VaccineInventoryNotFound{
		
		List<Inventory> inv = vaccineInventoryService.getVaccineInventoryBydate(date);
		
		return new ResponseEntity<List<Inventory>>(inv,HttpStatus.ACCEPTED);
		
	}
}