package com.covid.service;

import java.util.List;

import com.covid.exception.VaccineNotFoundException;
import com.covid.model.Vaccine;

public interface VaccineService {
	
	public Vaccine addVaccine(Vaccine vaccine,String key) throws VaccineNotFoundException;
	public List<Vaccine> getAllVaccine(String key)throws VaccineNotFoundException;
	public List<Vaccine> getVaccineByVccineName(String vaccineName, String key) throws VaccineNotFoundException;
	public Vaccine getVaccineById(Integer vaccineId ,String key)throws VaccineNotFoundException;
	public Vaccine updateVaccine(Vaccine vaccine,String key)throws VaccineNotFoundException;
	public Vaccine DeleteVaccine(Vaccine vaccine,String key) throws VaccineNotFoundException;

}
