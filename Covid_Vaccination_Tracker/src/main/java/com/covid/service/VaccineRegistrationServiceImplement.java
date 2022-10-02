package com.covid.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.covid.exception.LoginException;
import com.covid.exception.VaccineRegistrationException;
import com.covid.model.CustomerLoginSession;
import com.covid.model.Member;
import com.covid.model.VaccineRegistration;
import com.covid.repo.AdminLoginSessionDao;
import com.covid.repo.CustomerLoginSessionDao;
import com.covid.repo.VaccineRegistrationDao;

@Service
public class VaccineRegistrationServiceImplement implements VaccineRegistrationService {
      
	@Autowired
	private VaccineRegistrationDao daoVacRegistration;
	
	@Autowired
	private AdminLoginSessionDao daoALS;
	
	@Autowired
	private CustomerLoginSessionDao daoCLS;
	
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
	public VaccineRegistration addVaccineRegistration(VaccineRegistration registration,String key) throws VaccineRegistrationException {
		    
		  CustomerLoginSession cls= daoCLS.findByUuid(key);
		  if(cls==null)
		  {
			  throw new LoginException("Unauthorised access denied..");
		  }
		   
		    VaccineRegistration registraionDone=     daoVacRegistration.save(registration);
		    
		     return registraionDone;
	}
	@Override
	public VaccineRegistration getVaccineRegistration(String mobileNumber,String key) throws VaccineRegistrationException {
		      
		 CustomerLoginSession cls= daoCLS.findByUuid(key);
		  if(cls==null)
		  {
			  throw new LoginException("Unauthorised access denied..");
		  }
		    Optional<VaccineRegistration> optRegis= daoVacRegistration.findById(mobileNumber);
		    
		    if(optRegis.isPresent())
		    {
		    	  return optRegis.get();
		    }
		    throw new VaccineRegistrationException("Vaccine Registration not found with this Number..");
		    
	}
	@Override
	public List<Member> getAllMember(String mobileNumber,String key) throws VaccineRegistrationException {
		 CustomerLoginSession cls= daoCLS.findByUuid(key);
		  if(cls==null)
		  {
			  throw new LoginException("Unauthorised access denied..");
		  }
		  
		  Optional<VaccineRegistration> vrm= daoVacRegistration.findById(mobileNumber);
		  
		  if(!vrm.isPresent())
		  {
			  throw new VaccineRegistrationException("Mamber Not found with this number..");
		  }
		  
		   VaccineRegistration member= vrm.get();
		   
		   return member.getMemberList();
		   
	}
	@Override
	public VaccineRegistration updateVaccineRegistration(VaccineRegistration registration,String key) throws VaccineRegistrationException {
		            
		 CustomerLoginSession cls= daoCLS.findByUuid(key);
		  if(cls==null)
		  {
			  throw new LoginException("Unauthorised access denied..");
		  }
		           Optional<VaccineRegistration> optRegis= daoVacRegistration.findById(registration.getMobileNumber());
		           
		            if(!optRegis.isPresent())
		            {
		            	throw new VaccineRegistrationException("Vaccine registration doesn't exist..");
		            }
		            
		            daoVacRegistration.save(registration);
		            return optRegis.get();
	}
	@Override
	public boolean deleteVaccineRegistration(VaccineRegistration registration,String key) throws VaccineRegistrationException {
		     
		 CustomerLoginSession cls= daoCLS.findByUuid(key);
		  if(cls==null)
		  {
			  throw new LoginException("Unauthorised access denied..");
		  }
		        Optional<VaccineRegistration> optRegisDelete= daoVacRegistration.findById(registration.getMobileNumber());
		        
		          if(!optRegisDelete.isPresent())
		          {
		        	  throw new VaccineRegistrationException("Vaccine registration can not delete because its not exist..");
		        	  
		          }
		          
		          daoVacRegistration.delete(registration);
		          
		          return true;
	}
	
	

}
