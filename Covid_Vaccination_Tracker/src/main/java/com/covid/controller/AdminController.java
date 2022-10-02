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
import com.covid.exception.VaccineCenterException;
import com.covid.exception.VaccineInventoryNotFound;
import com.covid.exception.VaccineNotFoundException;
import com.covid.model.Admin;
import com.covid.model.IdCard;
import com.covid.model.Inventory;
import com.covid.model.Member;
import com.covid.model.VaccinationCenter;
import com.covid.model.Vaccine;
import com.covid.repo.AdminDao;
import com.covid.service.IdService;
import com.covid.service.MemberService;
import com.covid.service.VaccinationCenterService;
import com.covid.service.VaccineInventoryService;
import com.covid.service.VaccineService;

@RestController
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	AdminServiceImpl adminDao ;
	
	@Autowired
	AppointmentService appointmentService ;
	
	@Autowired
	private IdService idservice;
	
    @Autowired
	private MemberService memberservice;
	
	
	@Autowired
	VaccineInventoryService vaccineInventoryService;
	
@PostMapping("/")
	public ResponseEntity<Admin> saveAdmin(@Valid @RequestBody Admin admin) {
		
		Admin ad =  adminDao.registerAdmin(admin);
		
		return new ResponseEntity<Admin>(ad , HttpStatus.ACCEPTED) ;
	}
	
	@PutMapping("/")
	public ResponseEntity<Admin> UpdateAdmin(@Valid @RequestBody Admin admin) throws AdminException {
		
		Admin ad =  adminDao.updateAdmin(admin) ;
		
		return new ResponseEntity<Admin>(ad , HttpStatus.OK) ;
	}  
	
	@GetMapping("/{adminId}")
	public ResponseEntity<Admin>  getAdminByAdminId(@PathVariable("adminId") Integer adminId ) throws AdminException {
		
		Admin ad = adminDao.getAdminById(adminId);
		
		return new ResponseEntity<Admin>(ad , HttpStatus.OK) ;
	}
	@DeleteMapping("/{adminId}")
	public ResponseEntity<Admin> deleteAdminById(@PathVariable("adminId") Integer adminId ) throws AdminException {
		
		Admin ad =adminDao.deleteAdminById(adminId) ;
		
		return new ResponseEntity<Admin>(ad , HttpStatus.OK) ;
	}
	
	
	
	@GetMapping("/allAppointments/{key}")
	public ResponseEntity<List<Appointment>> getAllAppointments(@PathVariable("key") String key) throws AppointmentException {
		
		List<Appointment> app = appointmentService.getAllAppointment(key);
		return new ResponseEntity<>(app,HttpStatus.ACCEPTED);
	}
	
	@PostMapping("/addAppointmentTest/{key}")
	public ResponseEntity<Appointment> addAppointmentOnly(@Valid @RequestBody Appointment ap,@PathVariable String key ) throws VaccineRegistrationException, MemberNotFoundException, AppointmentException {
		
		Appointment app = appointmentService.addAppointmentTest(ap, key) ;
		return new ResponseEntity<>(app,HttpStatus.ACCEPTED);
	}
	
	
	
	@PostMapping("/addAppointment/{memberId}/{key}")
	public ResponseEntity<Appointment> addAppointmentByMemberId(@Valid @RequestBody Appointment ap ,@PathVariable Integer memberId ,@PathVariable String key ) throws VaccineRegistrationException, MemberNotFoundException, AppointmentException {
		
		Appointment app = appointmentService.addAppointment(ap, memberId, key);
		return new ResponseEntity<>(app,HttpStatus.ACCEPTED);
	}
	
	@GetMapping("/getAppointment/{bookingId}/{key}")
	public ResponseEntity<Appointment> getAppointment(@PathVariable("bookingId") long bookingId ,@PathVariable("key") String key) throws AppointmentException {
		
		Appointment app =  appointmentService.getAppointmentByBookingId(bookingId, key) ;
		
		return new ResponseEntity<>(app,HttpStatus.ACCEPTED);
	}
	
	@PutMapping("/updateAppointment/{key}")
	public ResponseEntity<Appointment> updateAppointment(@Valid @RequestBody Appointment app ,@PathVariable("key") String key ) throws AppointmentException{
		
		Appointment app2 = appointmentService.updateAppointment(app, key) ;
		
		return new ResponseEntity<>(app2,HttpStatus.ACCEPTED);
	}
	
	@DeleteMapping("/deleteAppointment/{bookingId}/{key}")
	public ResponseEntity<String> deleteAppointment(@PathVariable("bookingId") Long bookingId,@PathVariable("key")String key)throws AppointmentException{
		
		boolean ans = appointmentService.deleteAppointment(bookingId, key) ;
		
		 return new ResponseEntity<String>("Appointment Deleted succesfully", HttpStatus.GONE) ;
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
	@PostMapping("/addInventory/{key}")
	public ResponseEntity<Inventory> saveInventory(@RequestBody Inventory inv,@PathVariable("key") String key) throws VaccineInventoryNotFound {
		
		Inventory inventory = vaccineInventoryService.saveInventory(inv,key);
		
		return new ResponseEntity<Inventory>(inventory,HttpStatus.ACCEPTED);
		
	}
	
	@GetMapping("/allInventory/{key}")
	public ResponseEntity<List<Inventory>> getAlInventory(@PathVariable("key") String key ) throws VaccineInventoryNotFound {
		
		 List<Inventory> inventoryList =  vaccineInventoryService.allVaccineInventory(key);
		
		return new ResponseEntity<>(inventoryList,HttpStatus.ACCEPTED);
		
	}
	
	
	@GetMapping("/getInventoryById/{id}/{key}")
	public ResponseEntity<Inventory> getInventoryById(@PathVariable("id") Integer id,@PathVariable("key") String key) throws VaccineInventoryNotFound{
		
		Inventory inventory = vaccineInventoryService.getVaccineInventoryById(id,key);
		
		return new ResponseEntity<Inventory>(inventory,HttpStatus.ACCEPTED);
		
	}

	
	@PostMapping("/updateInventory/{key}")
	public ResponseEntity<Inventory> updateVaccineInventory(@RequestBody Inventory inv,@PathVariable("key") String key) throws VaccineInventoryNotFound{
		
		Inventory inventory = vaccineInventoryService.updateVaccineInventory(inv,key);
		
		return new ResponseEntity<Inventory>(inventory,HttpStatus.ACCEPTED);
		
	}
	
	
	@DeleteMapping("/deleteInventoryByID/{id}/{key}")
	public ResponseEntity<Boolean> deletVaccineInventoryByID(@PathVariable("id") Integer id,@PathVariable("key") String key) throws VaccineInventoryNotFound{
		
		Boolean ans =  vaccineInventoryService.deleteVaccineInventory(id,key);
		
		return new ResponseEntity<Boolean>(ans,HttpStatus.ACCEPTED);
		
	}
	
	
	@GetMapping("/getByDate/{date}/{key}")
	public ResponseEntity<List<Inventory>> getInventoryByDate(@PathVariable("date") @DateTimeFormat(pattern = "dd-MM-yyyy") LocalDate date,@PathVariable("key") String key) throws VaccineInventoryNotFound{
		
		List<Inventory> inv = vaccineInventoryService.getVaccineInventoryBydate(date,key);
		
		return new ResponseEntity<List<Inventory>>(inv,HttpStatus.ACCEPTED);
		
	}
	
	// Vaccine controler start--->
	
//	@Autowired
//	private IdService idservice;
//	
//	@Autowired
//	private MemberService memberservice;
//	
//	//for id
//	@PostMapping("/Id")
//	public ResponseEntity<IdCard> AddIdCardHandler(@RequestBody IdCard idcard) throws IdCardNotRegisterException{
//		
//		IdCard idc=idservice.addIdCard(idcard);
//		
//		return new ResponseEntity<IdCard>(idc,HttpStatus.CREATED);
//	}
//	
//	@GetMapping("/IdByAdhar/{AdharNo}")
//	public ResponseEntity<IdCard> FindByAdharHandler(@PathVariable Long AdharNo) throws IdCardNotFoundException{
//		
//		IdCard idc=idservice.getIdCardByAdharNo(AdharNo);
//		return new ResponseEntity<IdCard>(idc,HttpStatus.FOUND);
//		
//	}
//	
//	@GetMapping("/IdByPan/{PanNo}")
//	public ResponseEntity<IdCard> FindByPanHandler(@PathVariable String PanNo) throws IdCardNotFoundException{
//		
//		IdCard idc=idservice.getIdcardByPanNo(PanNo);
//		return new ResponseEntity<IdCard>(idc,HttpStatus.FOUND);
//		
//	}
//	
//	//for member
//	
//	@GetMapping("/MemberById/{id}")
//	public ResponseEntity<Member> FindByIdHandler(@PathVariable Integer id) throws MemberNotFoundException{
//		
//	Member member=	memberservice.getMemberById(id);
//	return new ResponseEntity<Member>(member,HttpStatus.FOUND);
//	
//		
//	}
//	
//	@GetMapping("/MemberByPan/{PanNo}")
//	public ResponseEntity<Member> FindByPanMemberHandler(@PathVariable String PanNo) throws MemberNotFoundException{
//		
//	Member member=	memberservice.getMemberByPanNo(PanNo);
//	return new ResponseEntity<Member>(member,HttpStatus.FOUND);
//	
//		
//	}
//	@GetMapping("/MemberByAdhar/{AdharNo}")
//	public ResponseEntity<Member> FindByAdharMemberHandler(@PathVariable Long AdharNo) throws MemberNotFoundException{
//		
//	Member member=	memberservice.getMemberByAdharNo(AdharNo);
//	return new ResponseEntity<Member>(member,HttpStatus.FOUND);
//	
//		
//	}
//	@GetMapping("/Members")
//	public ResponseEntity<List<Member>> FindAllMembersHandler() throws MemberNotRegisterException{
//		List<Member> members=memberservice.GetallTheMembers();
//		
//		return new ResponseEntity<List<Member>>(members,HttpStatus.FOUND);
//	}
//	@DeleteMapping("/Member")
//	public ResponseEntity<Boolean> DeleteMemberHandler(@RequestBody Member member) throws MemberNotFoundException{
//		Boolean ans=memberservice.deleteMemberRecord(member);
//		return new ResponseEntity<Boolean>(ans,HttpStatus.ACCEPTED);
//		
//	}
//
//	@PutMapping("/Member")
//	public ResponseEntity<Member> UpdateMemberHandler(@RequestBody Member member) throws MemberNotFoundException{
//		Member memb=memberservice.updateMember(member);
//		
//		return new ResponseEntity<Member>(member,HttpStatus.FOUND);
//	}
//	@PostMapping("/Member/{Number}")
//	public ResponseEntity<Member> AddMemberHandler(@RequestBody Member member,@PathVariable String Number) throws MemberNotFoundException{
//		
//		Member m=memberservice.addMemberbyMobileNo(member, Number);
//		
//		return new ResponseEntity<Member>(m,HttpStatus.CREATED);
//	}
	
	// vaccine controler start-->>>
	
	@Autowired
	  private VaccineService  serviceVaccine;
	
	@PostMapping("/vaccine")
	  public ResponseEntity<Vaccine> AddVaccine( @RequestBody Vaccine vaccine, @RequestParam String key) throws VaccineNotFoundException{
		  
		     return new ResponseEntity<Vaccine>(serviceVaccine.addVaccine(vaccine,key),HttpStatus.CREATED);
	  }
	
	@GetMapping("/vaccines")
	public ResponseEntity<List<Vaccine>> gettAllVaccine(@RequestParam String key) throws VaccineNotFoundException{
		
		   List<Vaccine> allvaccine= serviceVaccine.getAllVaccine(key);
		   
		   return new ResponseEntity<List<Vaccine>>(allvaccine,HttpStatus.OK);
	}
	
	@GetMapping("/vaccines/{vaccineName}")
	public ResponseEntity<List<Vaccine>> getVaccineByName(  @PathVariable("vaccineName")  String vaccineName,@RequestParam String key) throws VaccineNotFoundException{
		
		       List<Vaccine> vaccineByName= serviceVaccine.getVaccineByVccineName(vaccineName,key);
		       
		       return new ResponseEntity<List<Vaccine>>(vaccineByName, HttpStatus.OK);
	}
	
	@GetMapping("vaccine/{vaccineId}")
	public ResponseEntity<Vaccine> getVaccinesById( @PathVariable("vaccineId") Integer vaccineId,@RequestParam String key) throws VaccineNotFoundException{
		
		  return new ResponseEntity<Vaccine>(serviceVaccine.getVaccineById(vaccineId,key),HttpStatus.OK);
	}

	@PutMapping("/vaccine")
	  public ResponseEntity<Vaccine> UpdateVaccine( @RequestBody Vaccine vaccine,@RequestParam String key) throws VaccineNotFoundException{
		  
		     return new ResponseEntity<Vaccine>(serviceVaccine.updateVaccine(vaccine,key),HttpStatus.OK);
	  }
	
	@DeleteMapping("/vaccine")
	public ResponseEntity<Vaccine> DeleteVaccine( @RequestBody Vaccine vaccine,@RequestParam String key) throws VaccineNotFoundException{
		
		return new ResponseEntity<Vaccine>(serviceVaccine.DeleteVaccine(vaccine,key),HttpStatus.OK);
	}
	
	
	vaccine center service control
	
	
	@GetMapping("/vaccination_centers")
	public ResponseEntity<List<VaccinationCenter>> getVaccineCenters(@RequestParam String key) throws VaccineCenterException {
		return new ResponseEntity<List<VaccinationCenter>>(VaccinationCenterService.allVaccineCenters(key), HttpStatus.OK);
	}

	@PostMapping("/vaccination_center")
	public ResponseEntity<VaccinationCenter> addVaccineCenter(@RequestBody VaccinationCenter center,@RequestParam String key) throws VaccineCenterException {
		return new ResponseEntity<VaccinationCenter>(VaccinationCenterService.addVaccineCenter(center,key),
				HttpStatus.CREATED);
	}

	@GetMapping("/vaccination_center/{id}")
	public ResponseEntity<VaccinationCenter> addVaccineCenter(@PathVariable("id") Integer id,@RequestParam String key) {
		return new ResponseEntity<VaccinationCenter>(VaccinationCenterService.getVaccineCenter(id,key), HttpStatus.FOUND);
	}

	@PutMapping("/vaccination_center")
	public ResponseEntity<VaccinationCenter> updateVaccineCenter(@RequestBody VaccinationCenter center,@RequestParam String key) {
		return new ResponseEntity<VaccinationCenter>(VaccinationCenterService.updateVaccineCenter(center,key),
				HttpStatus.OK);
	}

	@DeleteMapping("/vaccination_center")
	public ResponseEntity<String> deleteVaccineCenter(@RequestBody VaccinationCenter center,@RequestParam String key) {
		return new ResponseEntity<>("vaccine center deleted : " + VaccinationCenterService.deleteVaccineCenter(center,key),
				HttpStatus.OK);
	}

}
