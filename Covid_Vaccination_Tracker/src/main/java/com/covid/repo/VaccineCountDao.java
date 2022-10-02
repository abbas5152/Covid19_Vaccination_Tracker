package com.covid.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.covid.model.VaccineCount;

public interface VaccineCountDao extends JpaRepository<VaccineCount, Integer>{

}
