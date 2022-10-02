package com.covid.service;

import java.util.List;

import com.covid.exception.VaccineRegistrationException;
import com.covid.model.Member;
import com.covid.model.VaccineRegistration;

public interface VaccineRegistrationService {
           
	public List<VaccineRegistration> allVaccineRegistration() throws VaccineRegistrationException;
	public VaccineRegistration addVaccineRegistration(VaccineRegistration registration, String key) throws VaccineRegistrationException;

	public VaccineRegistration getVaccineRegistration(String mobileNumber,String key) throws VaccineRegistrationException;

	public List<Member> getAllMember(String mobileNumber,String key) throws VaccineRegistrationException ;

	public VaccineRegistration updateVaccineRegistration(VaccineRegistration registration,String key) throws VaccineRegistrationException;

	  public boolean deleteVaccineRegistration(VaccineRegistration registration,String key) throws VaccineRegistrationException;
}   
