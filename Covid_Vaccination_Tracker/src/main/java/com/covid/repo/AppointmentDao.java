package com.covid.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.covid.model.Appointment;
import com.covid.model.Member;

public interface AppointmentDao extends JpaRepository<Appointment, Integer> {
	
	
  public List<Appointment> findByMember(Member member);

    public Optional<Appointment> findByBookingId(Long bookingId);
}
