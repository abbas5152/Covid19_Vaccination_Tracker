package com.covid.service;

import java.util.List;

import com.covid.exception.VaccineNotFoundException;
import com.covid.model.Vaccine;

public interface VaccineService {
	
	public Vaccine addVaccine(Vaccine vaccine) throws VaccineNotFoundException;
	public List<Vaccine> getVaccineByName(String VaccineName) throws VaccineNotFoundException;
	public Vaccine getVaccinebyId(Integer vaccineId) throws VaccineNotFoundException;
	public Vaccine UpdateVaccine(Vaccine vaccine) throws VaccineNotFoundException;
	public Vaccine DeleteVaccine(Vaccine vaccine) throws VaccineNotFoundException;

}
