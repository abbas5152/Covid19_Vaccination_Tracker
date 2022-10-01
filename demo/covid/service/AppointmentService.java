package com.covid.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.covid.exception.AppointmentException;
import com.covid.exception.MemberNotFoundException;
import com.covid.exception.VaccineRegistrationException;
import com.covid.model.Appointment;


@Service
public interface AppointmentService {

	public List<Appointment> getAllAppointment(String key) throws AppointmentException;

	public Appointment getAppointmentByBookingId(Long bookingId,String key)throws AppointmentException;;

	public Appointment addAppointment(Appointment app, Integer memId,String key) throws VaccineRegistrationException, MemberNotFoundException, AppointmentException;

	public Appointment updateAppointment(Appointment app,String key)throws AppointmentException;;

	public boolean deleteAppointment(Long bookingId,String key)throws AppointmentException;
	
}
