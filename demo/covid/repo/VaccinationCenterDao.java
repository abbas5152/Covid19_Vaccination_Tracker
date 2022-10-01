package com.covid.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.covid.model.VaccinationCenter;

@Repository
public interface VaccinationCenterDao extends JpaRepository<VaccinationCenter, Integer>{

}
