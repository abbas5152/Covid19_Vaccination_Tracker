package com.covid.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.covid.exception.VaccineNotFoundException;
import com.covid.model.Vaccine;
import com.covid.repo.VaccineDao;

@Service
public class VaccineServiceImplement implements VaccineService {

	     @Autowired
	      private VaccineDao daoVaccine;
	@Override
	public Vaccine addVaccine(Vaccine vaccine) throws VaccineNotFoundException {
		      
		              Vaccine Savedvaccine=   daoVaccine.save(vaccine);
		              
		              return Savedvaccine;
	}
	@Override
	public List<Vaccine> getAllVaccine() throws VaccineNotFoundException {
		    List<Vaccine> allVaccine= daoVaccine.findAll();
		    
		    if(allVaccine.size()>0)
		    {
		    	return allVaccine;
		    }
		    
		    throw new VaccineNotFoundException("Vaccine not found..");
	}
	@Override
	public List<Vaccine> getVaccineByVccineName(String vaccineName) throws VaccineNotFoundException {
		      
		          List<Vaccine> vaccinesbyName= daoVaccine.findByVaccineName(vaccineName);
		          
		          if(vaccinesbyName.size()>0) {
		        	  
		        	    return vaccinesbyName;
		          }
		          
		          throw new VaccineNotFoundException("This vaccine doesn't exist in vaccine list..");
	}
	@Override
	public Vaccine getVaccineById(Integer vaccineId) throws VaccineNotFoundException {
		      
		        Optional<Vaccine> opt=   daoVaccine.findById(vaccineId);
		        
		        if(opt.isPresent()) {
		        	
		        	    return opt.get();
		        }
		        
		        throw new VaccineNotFoundException("Vaccine can not find with this Id Number - "+ vaccineId);
	}
	@Override
	public Vaccine updateVaccine(Vaccine vaccine) throws VaccineNotFoundException {
		      
		       Optional<Vaccine> update_opt= daoVaccine.findById(vaccine.getVaccineId());
		       
		       if(!update_opt.isPresent())
		       {
		    	    throw new VaccineNotFoundException("vaccine not updated because vaccine not found");
		       }
		       
		       daoVaccine.save(vaccine);
		       
		       return update_opt.get();
	}
	@Override
	public Vaccine DeleteVaccine(Vaccine vaccine) throws VaccineNotFoundException {
	             
		    Optional<Vaccine> deletevaccine= daoVaccine.findById(vaccine.getVaccineId());
		    
		    if(!deletevaccine.isPresent())
		    {
		    	   throw new VaccineNotFoundException("vaccine not deleted..");
		    }
		    
		    daoVaccine.delete(vaccine);
		    
		    return  vaccine;
	}

}
