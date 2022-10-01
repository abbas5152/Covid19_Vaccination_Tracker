package com.covid.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.covid.model.Appointment;

public interface AppointmentDao extends JpaRepository<Appointment, Integer> {

	Optional<Appointment> findById(Long bookingId);

}
