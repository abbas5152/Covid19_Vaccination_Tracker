package com.covid.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
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

import com.covid.exception.IdCardNotRegisterException;
import com.covid.exception.MemberNotFoundException;
import com.covid.exception.VaccineCenterException;
import com.covid.exception.VaccineRegistrationException;
import com.covid.model.Customer;
import com.covid.model.IdCard;
import com.covid.model.Member;
import com.covid.model.VaccinationCenter;
import com.covid.model.VaccineRegistration;
import com.covid.repo.CustomerDao;
import com.covid.service.IdService;
import com.covid.service.LoginService;
import com.covid.service.MemberService;
import com.covid.service.VaccinationCenterService;
import com.covid.service.VaccineRegistrationService;

@RestController
@RequestMapping("/customer")
public class CustomerController {

	@Autowired
	CustomerService customerDao ;
	
	@Autowired
	AppointmentService appointmentService ;
	
	@Autowired
	private IdService idservice;
	
	@Autowired
	private MemberService memberservice;
	
	@PostMapping("/")
	public ResponseEntity<Customer> saveCustomer(@Valid @RequestBody Customer customer) {
		
		Customer cus =  customerDao.registerCustomer(customer) ;
		return new ResponseEntity<Customer>(cus,HttpStatus.CREATED) ;
	}
	
	@PutMapping("/")
	public ResponseEntity<Customer> UpdateCustomer(@Valid @RequestBody Customer customer) throws CustomerException {
		
		Customer cus =  customerDao.updateCustomer(customer);
		return new ResponseEntity<Customer>(cus,HttpStatus.OK) ;
	}
	
	@GetMapping("/{customerId}")
	public ResponseEntity<Customer> getCusotmerByAdminId(@PathVariable("cusotmerId") Integer customerId ) throws CustomerException {
		
		Customer cus =  customerDao.getCustomerById(customerId);
		
		return new ResponseEntity<Customer>(cus,HttpStatus.OK) ;
	}
	@DeleteMapping("/{customerId}")
	public ResponseEntity<Customer> deleteCustomerById(@PathVariable("customerId") Integer customerId ) throws CustomerException {
		
		Customer cus =  customerDao.deleteCustomerById(customerId);
		return new ResponseEntity<Customer>(cus,HttpStatus.OK) ;
	}
	
	@GetMapping("/getAppointment/{bookingId}/{key}")
	public ResponseEntity<Appointment> getAppointment(@PathVariable("bookingId") long bookingId ,@PathVariable("key") String key) throws AppointmentException {
		
		Appointment app =  appointmentService.getAppointmentByBookingId(bookingId, key) ;
		
		return new ResponseEntity<>(app,HttpStatus.ACCEPTED);
	}
	@PostMapping("/Id")
	public ResponseEntity<IdCard> AddIdCardHandler(@Valid @RequestBody IdCard idcard,@RequestParam String key) throws IdCardNotRegisterException{
		
		IdCard idc=idservice.addIdCard(idcard, key);
		
		return new ResponseEntity<IdCard>(idc,HttpStatus.CREATED);
	}
	@GetMapping("/MemberById/{id}")
	public ResponseEntity<Member> FindByIdHandler(@PathVariable Integer id,@RequestParam String key) throws MemberNotFoundException{
		
	Member member=	memberservice.getMemberById(id, key);
	return new ResponseEntity<Member>(member,HttpStatus.FOUND);
	
		
	}
	
	
	@DeleteMapping("/Member")
	public ResponseEntity<Boolean> DeleteMemberHandler(@RequestBody Member member,@RequestParam String key) throws MemberNotFoundException{
		Boolean ans=memberservice.deleteMemberRecord(member, key);
		return new ResponseEntity<Boolean>(ans,HttpStatus.ACCEPTED);
		
	}
	@PutMapping("/Member")
	public ResponseEntity<Member> UpdateMemberHandler(@Valid @RequestBody Member member,@RequestParam String key) throws MemberNotFoundException{
		Member memb=memberservice.updateMember(member, key);
		
		return new ResponseEntity<Member>(member,HttpStatus.FOUND);
	}
	@PostMapping("/Member/{Number}")
	public ResponseEntity<Member> AddMemberHandler(@Valid @RequestBody Member member,@PathVariable String Number,@RequestParam String key) throws MemberNotFoundException{
		
		Member m=memberservice.addMemberbyMobileNo(member, Number, key);
		
		return new ResponseEntity<Member>(m,HttpStatus.CREATED);
	}
	
	// vaccine Registration controler start --->
	
	
//	@Autowired
//	CustomerDao customerDao ;
//	
//	@PostMapping("customer")
//	public Customer saveCustomer(@RequestBody Customer customer) {
//		return customerDao.save(customer) ;
//	}
	
	// vaccine Registation controler ---->
	
	@Autowired
    private VaccineRegistrationService registrationService;
	
	@Autowired
	private LoginService loginservice;
	
	
	
	
	@GetMapping("/Allregistrations")
	     public ResponseEntity<List<VaccineRegistration>> getallRegistration() throws VaccineRegistrationException{
	    	 
	    	        List<VaccineRegistration> allreigs= registrationService.allVaccineRegistration();
	    	        
	    	        return new ResponseEntity<List<VaccineRegistration>>(allreigs, HttpStatus.OK);
	    	        
	     }
	
	@GetMapping("/member/{mobileNumber}")
	 public ResponseEntity<List<Member>> getAllMemberByNu(@PathVariable("mobileNumber") String mobileNumber,@RequestParam String key) throws VaccineRegistrationException{
		 
		    return new ResponseEntity<List<Member>>(registrationService.getAllMember(mobileNumber, key),HttpStatus.OK);
	 }
	
	
	  @PostMapping("/registration")
	   public ResponseEntity<VaccineRegistration> addRegistration( @RequestBody VaccineRegistration registration,@RequestParam String key) throws VaccineRegistrationException{
		   
		      return new ResponseEntity<VaccineRegistration>(registrationService.addVaccineRegistration(registration,key), HttpStatus.OK);
	   }
	  
	  @GetMapping("/registrations/{mobileNumber}")
	  public ResponseEntity<VaccineRegistration> getVaccineRegistration( @PathVariable("mobileNumber") String mobileNumber,@RequestParam String key) throws VaccineRegistrationException{
		  
		    return new ResponseEntity<VaccineRegistration>(registrationService.getVaccineRegistration(mobileNumber,key),HttpStatus.OK);
	  }
	  
	  @PutMapping("/registration")
	  public ResponseEntity<VaccineRegistration> UpdateRegistration( @RequestBody VaccineRegistration registration,@RequestParam String key) throws VaccineRegistrationException{
		  
		      return new ResponseEntity<VaccineRegistration>(registrationService.updateVaccineRegistration(registration,key),HttpStatus.OK);
	  }
	  
	  @DeleteMapping("/registration")
	  public boolean DeleteRegistraion( @RequestBody VaccineRegistration registration,@RequestParam String key) throws VaccineRegistrationException{
		        
		      boolean x=     registrationService.deleteVaccineRegistration(registration,key);
		              
		               
		             return x;
	  }
	
	
	//vaccine center service user
	
//	@GetMapping("/vaccination_centers")
//	public ResponseEntity<List<VaccinationCenter>> getVaccineCenters(@RequestParam String key) throws VaccineCenterException {
//		return new ResponseEntity<List<VaccinationCenter>>(VaccinationCenterService.allVaccineCenters(key), HttpStatus.OK);
//	}
	
}
