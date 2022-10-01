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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.covid.exception.IdCardNotFoundException;
import com.covid.exception.MemberNotFoundException;
import com.covid.exception.MemberNotRegisterException;
import com.covid.exception.VaccineInventoryNotFound;
import com.covid.model.Admin;
import com.covid.model.IdCard;
import com.covid.model.Inventory;
import com.covid.model.Member;
import com.covid.repo.AdminDao;
import com.covid.service.IdService;
import com.covid.service.MemberService;
import com.covid.service.VaccineInventoryService;

@RestController
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	AdminDao adminDao ;
	
	@Autowired
	private IdService idservice;
	
	@Autowired
	private MemberService memberservice;
	
	
	@Autowired
	VaccineInventoryService vaccineInventoryService;
	
	@PostMapping("/")
	public Admin saveAdmin(@RequestBody Admin admin) {
		
		return adminDao.save(admin) ;
		
	}
	

	@GetMapping("/IdByAdhar/{AdharNo}")
	public ResponseEntity<IdCard> FindByAdharHandler(@PathVariable Long AdharNo,@RequestParam String key) throws IdCardNotFoundException{
		
		IdCard idc=idservice.getIdCardByAdharNo(AdharNo, key);
		return new ResponseEntity<IdCard>(idc,HttpStatus.FOUND);
		
	}
	
	@GetMapping("/IdByPan/{PanNo}")
	public ResponseEntity<IdCard> FindByPanHandler(@PathVariable String PanNo,@RequestParam String key) throws IdCardNotFoundException{
		
		IdCard idc=idservice.getIdcardByPanNo(PanNo, key);
		return new ResponseEntity<IdCard>(idc,HttpStatus.FOUND);
		
	}
	
	@GetMapping("/Members")
	public ResponseEntity<List<Member>> FindAllMembersHandler(@RequestParam String key) throws MemberNotRegisterException{
		List<Member> members=memberservice.GetallTheMembers(key);
		
		return new ResponseEntity<List<Member>>(members,HttpStatus.FOUND);
	}
	@GetMapping("/MemberById/{id}")
	public ResponseEntity<Member> FindByIdHandler(@PathVariable Integer id,@RequestParam String key) throws MemberNotFoundException{
		
	Member member=	memberservice.getMemberById(id, key);
	return new ResponseEntity<Member>(member,HttpStatus.FOUND);
	
		
	}
	@PutMapping("/Member")
	public ResponseEntity<Member> UpdateMemberHandler(@RequestBody Member member,@RequestParam String key) throws MemberNotFoundException{
		Member memb=memberservice.updateMember(member, key);
		
		return new ResponseEntity<Member>(member,HttpStatus.FOUND);
	}
	@GetMapping("/MemberByPan/{PanNo}")
	public ResponseEntity<Member> FindByPanMemberHandler(@PathVariable String PanNo,@RequestParam String key) throws MemberNotFoundException{
		
	Member member=	memberservice.getMemberByPanNo(PanNo, key);
	return new ResponseEntity<Member>(member,HttpStatus.FOUND);
	
		
	}
	@GetMapping("/MemberByAdhar/{AdharNo}")
	public ResponseEntity<Member> FindByAdharMemberHandler(@PathVariable Long AdharNo,@RequestParam String key) throws MemberNotFoundException{
		
	Member member=	memberservice.getMemberByAdharNo(AdharNo, key);
	return new ResponseEntity<Member>(member,HttpStatus.FOUND);
	
		
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