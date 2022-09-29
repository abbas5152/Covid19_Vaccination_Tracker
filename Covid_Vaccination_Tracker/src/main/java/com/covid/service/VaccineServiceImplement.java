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
	private VaccineDao vaccineDao;
	

	@Override
	public Vaccine addVaccine(Vaccine vaccine) throws VaccineNotFoundException {
	          
		      Vaccine savedVaccine=   vaccineDao.save(vaccine);
		      
		      return savedVaccine;
	}


	@Override
	public List<Vaccine> getVaccineByName(String VaccineName) throws VaccineNotFoundException {
		         
		             List<Vaccine>  list =  vaccineDao.findByVaccineName(VaccineName);
		             
		             if(list.size()>0)
		             {
		            	  return list;
		             }
		             throw new VaccineNotFoundException("Vaccine not found...");
	}


	@Override
	public Vaccine getVaccinebyId(Integer vaccineId) throws VaccineNotFoundException {
	           
		    Optional<Vaccine> opt=     vaccineDao.findById(vaccineId);
		    
		    if(!opt.isPresent())
		    {
		    	throw new VaccineNotFoundException("Vaccine not find with this Id");
		    }
		    
		    return opt.get();
	}


	@Override
	public Vaccine UpdateVaccine(Vaccine vaccine) throws VaccineNotFoundException {
		      
		          Optional<Vaccine> opt = vaccineDao.findById(vaccine.getVaccineId());
		          
		          if(!opt.isPresent())
		          {
		        	  throw new VaccineNotFoundException("Vaccine doesn't exist , whichever you want to update.");
		          }
		          
		          return vaccineDao.save(vaccine);
	}


	@Override
	public Vaccine DeleteVaccine(Vaccine vaccine) throws VaccineNotFoundException {
	          
		        Optional<Vaccine> deletevaccine=  vaccineDao.findById(vaccine.getVaccineId());
		        
		        if(!deletevaccine.isPresent())
		        {
		        	throw new VaccineNotFoundException("You can not delete vaccine brcause vaccine doesn't exist..");
		        }
		        
		             vaccineDao.delete(deletevaccine.get());
		             
		             return deletevaccine.get();
	}

}
