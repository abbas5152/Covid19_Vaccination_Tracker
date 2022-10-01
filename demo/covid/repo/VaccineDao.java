package com.covid.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.covid.model.Vaccine;

public interface VaccineDao extends JpaRepository<Vaccine, Integer> {
	
	         public List<Vaccine> findByVaccineName(String vaccineName);

}
