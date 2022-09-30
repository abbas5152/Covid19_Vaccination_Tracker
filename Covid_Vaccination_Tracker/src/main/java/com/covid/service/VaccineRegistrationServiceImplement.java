package com.covid.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.covid.exception.VaccineRegistrationException;
import com.covid.model.Member;
import com.covid.model.VaccineRegistration;
import com.covid.repo.VaccineRegistrationDao;

@Service
public class VaccineRegistrationServiceImplement implements VaccineRegistrationService {
      
	@Autowired
	private VaccineRegistrationDao daoVacRegistration;
	
//	@Autowired

	@Override
	public List<VaccineRegistration> allVaccineRegistration() throws VaccineRegistrationException {
		   
		     List<VaccineRegistration>   allRegistration=  daoVacRegistration.findAll();
		     
		       if(allRegistration.size()>=0)
		       {
		    	   throw new VaccineRegistrationException("Registration of  vaccine doesn't exist..");
		       }
		       
		       return allRegistration;
	}
	@Override
	public VaccineRegistration addVaccineRegistration(VaccineRegistration registration) throws VaccineRegistrationException {
		          
		    VaccineRegistration registraionDone=     daoVacRegistration.save(registration);
		    
		     return registraionDone;
	}
	@Override
	public VaccineRegistration getVaccineRegistration(String mobileNumber) throws VaccineRegistrationException {
		      
		    Optional<VaccineRegistration> optRegis= daoVacRegistration.findById(mobileNumber);
		    
		    if(optRegis.isPresent())
		    {
		    	  return optRegis.get();
		    }
		    throw new VaccineRegistrationException("Vaccine Registration not found with this Number..");
		    
	}
	@Override
	public List<Member> getAllMember(String mobileNumber) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public VaccineRegistration updateVaccineRegistration(VaccineRegistration registration) throws VaccineRegistrationException {
		            
		           Optional<VaccineRegistration> optRegis= daoVacRegistration.findById(registration.getMobileNumber());
		           
		            if(!optRegis.isPresent())
		            {
		            	throw new VaccineRegistrationException("Vaccine registration doesn't exist..");
		            }
		            
		            daoVacRegistration.save(registration);
		            return optRegis.get();
	}
	@Override
	public boolean deleteVaccineRegistration(VaccineRegistration registration) throws VaccineRegistrationException {
		     
		        Optional<VaccineRegistration> optRegisDelete= daoVacRegistration.findById(registration.getMobileNumber());
		        
		          if(!optRegisDelete.isPresent())
		          {
		        	  throw new VaccineRegistrationException("Vaccine registration can not delete because its not exist..");
		        	  
		          }
		          
		          daoVacRegistration.delete(registration);
		          
		          return true;
	}
	
	

}
