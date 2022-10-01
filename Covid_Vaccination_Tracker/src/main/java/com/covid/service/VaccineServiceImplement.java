package com.covid.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.covid.exception.LoginException;
import com.covid.exception.VaccineNotFoundException;
import com.covid.model.Admin;
import com.covid.model.AdminLoginSession;
import com.covid.model.CustomerLoginSession;
import com.covid.model.Vaccine;
import com.covid.repo.AdminLoginSessionDao;
import com.covid.repo.CustomerLoginSessionDao;
import com.covid.repo.VaccineDao;

@Service
public class VaccineServiceImplement implements VaccineService {
     
	@Autowired
	  private AdminLoginSessionDao daoAdminLSes;
	
	private CustomerLoginSessionDao daoCLS;
	
	     @Autowired
	      private VaccineDao daoVaccine;
	@Override
	public Vaccine addVaccine(Vaccine vaccine,String key) throws VaccineNotFoundException {
		
		      AdminLoginSession optAdminses= daoAdminLSes.findByUuid(key);
//		  Optional<CustomerLoginSession> optCustLoSes= Optional.of(daoCLS.findByUuid(key));
		      
		      if(optAdminses==null)
		      {
		    	   throw new LoginException("Unathrosied access denied..");
		      }
		      
		              Vaccine Savedvaccine=   daoVaccine.save(vaccine);
		              
		              return Savedvaccine;
	}
	@Override
	public List<Vaccine> getAllVaccine(String key) throws VaccineNotFoundException {
		    List<Vaccine> allVaccine= daoVaccine.findAll();
		    
		    AdminLoginSession optAdminses= daoAdminLSes.findByUuid(key);
			  CustomerLoginSession optCustLoSes= daoCLS.findByUuid(key);
			      
			      if(optAdminses==null && optCustLoSes==null)
			      {
			    	   throw new LoginException("Unathrosied access denied..");
			      }
		      
		    if(allVaccine.size()>0)
		    {
		    	return allVaccine;
		    }
		    
		    throw new VaccineNotFoundException("Vaccine not found..");
	}
	@Override
	public List<Vaccine> getVaccineByVccineName(String vaccineName,String key) throws VaccineNotFoundException {
		    
		  AdminLoginSession optAdminses= daoAdminLSes.findByUuid(key);

		      
		      if(optAdminses==null)
		      {
		    	   throw new LoginException("Unathrosied access denied..");
		      }
		
		          List<Vaccine> vaccinesbyName= daoVaccine.findByVaccineName(vaccineName);
		          
		          if(vaccinesbyName.size()>0) {
		        	  
		        	    return vaccinesbyName;
		          }
		          
		          throw new VaccineNotFoundException("This vaccine doesn't exist in vaccine list..");
	}
	@Override
	public Vaccine getVaccineById(Integer vaccineId,String key) throws VaccineNotFoundException {
		      
		 AdminLoginSession optAdminses= daoAdminLSes.findByUuid(key);

	      
	      if(optAdminses==null)
	      {
	    	   throw new LoginException("Unathrosied access denied..");
	      }
		        Optional<Vaccine> opt=   daoVaccine.findById(vaccineId);
		        
		        if(opt.isPresent()) {
		        	
		        	    return opt.get();
		        }
		        
		        throw new VaccineNotFoundException("Vaccine can not find with this Id Number - "+ vaccineId);
	}
	@Override
	public Vaccine updateVaccine(Vaccine vaccine,String key) throws VaccineNotFoundException {
		
		 AdminLoginSession optAdminses= daoAdminLSes.findByUuid(key);

	      
	      if(optAdminses==null)
	      {
	    	   throw new LoginException("Unathrosied access denied..");
	      }
		      
		       Optional<Vaccine> update_opt= daoVaccine.findById(vaccine.getVaccineId());
		       
		       if(!update_opt.isPresent())
		       {
		    	    throw new VaccineNotFoundException("vaccine not updated because vaccine not found");
		       }
		       
		       daoVaccine.save(vaccine);
		       
		       return update_opt.get();
	}
	@Override
	public Vaccine DeleteVaccine(Vaccine vaccine,String key) throws VaccineNotFoundException {
		
		 AdminLoginSession optAdminses= daoAdminLSes.findByUuid(key);

	      
	      if(optAdminses==null)
	      {
	    	   throw new LoginException("Unathrosied access denied..");
	      }
	             
		    Optional<Vaccine> deletevaccine= daoVaccine.findById(vaccine.getVaccineId());
		    
		    if(!deletevaccine.isPresent())
		    {
		    	   throw new VaccineNotFoundException("vaccine not deleted..");
		    }
		    
		    daoVaccine.delete(vaccine);
		    
		    return  vaccine;
	}

}
