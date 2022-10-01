package com.covid.service;

import java.util.List;

import com.covid.exception.VaccineCenterException;
import com.covid.exception.VaccineCenterNotFoundException;
import com.covid.model.VaccinationCenter;

public interface VaccinationCenterService {

	public List<VaccinationCenter> allVaccineCenters(String key) throws VaccineCenterException;

	public VaccinationCenter getVaccineCenter(Integer centerid,String key) throws VaccineCenterNotFoundException;

	public VaccinationCenter addVaccineCenter(VaccinationCenter center,String key) throws VaccineCenterException;

	public VaccinationCenter updateVaccineCenter(VaccinationCenter center,String key) throws VaccineCenterNotFoundException;

	public boolean deleteVaccineCenter(VaccinationCenter center,String key) throws VaccineCenterNotFoundException;
}