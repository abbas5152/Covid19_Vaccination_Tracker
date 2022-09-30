package com.covid.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.covid.model.Appointment;

public interface AppointmentDao extends JpaRepository<Appointment, Integer> {

}
