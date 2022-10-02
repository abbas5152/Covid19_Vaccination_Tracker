package com.covid.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.covid.model.Appointment;
import com.covid.model.Member;

@Repository
public interface AppointmentDao extends JpaRepository<Appointment, Long> {
	
	
  public List<Appointment> findByMember(Member member);

  Optional<Appointment> findByBookingId(Long bookingId);
  }
