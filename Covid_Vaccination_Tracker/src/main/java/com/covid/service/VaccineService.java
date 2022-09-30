package com.covid.service;

import java.util.List;

import com.covid.exception.VaccineNotFoundException;
import com.covid.model.Vaccine;

public interface VaccineService {
	
	public Vaccine addVaccine(Vaccine vaccine) throws VaccineNotFoundException;
	public List<Vaccine> getAllVaccine()throws VaccineNotFoundException;
	public List<Vaccine> getVaccineByVccineName(String vaccineName) throws VaccineNotFoundException;
	public Vaccine getVaccineById(Integer vaccineId)throws VaccineNotFoundException;
	public Vaccine updateVaccine(Vaccine vaccine)throws VaccineNotFoundException;
	public Vaccine DeleteVaccine(Vaccine vaccine) throws VaccineNotFoundException;

}
