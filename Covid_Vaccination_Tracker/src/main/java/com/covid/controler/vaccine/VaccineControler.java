package com.covid.controler.vaccine;

import java.util.List;

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
import org.springframework.web.bind.annotation.RestController;

import com.covid.exception.VaccineNotFoundException;
import com.covid.model.Vaccine;
import com.covid.service.VaccineService;

@RestController
@RequestMapping("/service")
public class VaccineControler {
	
	@Autowired
	    private VaccineService vaccineService;
	
	@PostMapping("/vaccine")
	  public ResponseEntity<Vaccine> SaveVaccine( @RequestBody Vaccine vaccine) throws VaccineNotFoundException{
		                          
		     return new ResponseEntity<Vaccine>(vaccineService.addVaccine(vaccine),HttpStatus.CREATED);
	  }
	
	@GetMapping("vaccine/{vaccineName}")
	public ResponseEntity<List<Vaccine>> getVaccineByName( @PathVariable("vaccineName") String vaccineName) throws VaccineNotFoundException{
		
		      List<Vaccine> listvaccine= vaccineService.getVaccineByName(vaccineName);
		      
		      return new ResponseEntity<List<Vaccine>>(listvaccine,HttpStatus.OK);
		      
	}
	
	@GetMapping("vaccines/{id}")
	public ResponseEntity<Vaccine> GetVaccineById( @PathVariable("id")  Integer id) throws VaccineNotFoundException{
		
		return new ResponseEntity<Vaccine>(vaccineService.getVaccinebyId(id),HttpStatus.OK);
	}
	
	@PutMapping("/vaccine")
	public ResponseEntity<Vaccine> UpdateVaccine( @RequestBody Vaccine vaccine) throws VaccineNotFoundException{
		
		        return new ResponseEntity<Vaccine>(vaccineService.UpdateVaccine(vaccine),HttpStatus.OK);
	}
	
	@DeleteMapping("/vaccine")
	public ResponseEntity<Vaccine> deleteVaccine(@RequestBody Vaccine vaccine) throws VaccineNotFoundException{
		
		  return  new ResponseEntity<Vaccine>(vaccineService.DeleteVaccine(vaccine), HttpStatus.OK);
		  
	}

}
