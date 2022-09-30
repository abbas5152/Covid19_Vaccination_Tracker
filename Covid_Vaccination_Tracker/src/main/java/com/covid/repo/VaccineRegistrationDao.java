package com.covid.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.covid.model.VaccineRegistration;

public interface VaccineRegistrationDao extends JpaRepository<VaccineRegistration, String>{

}
