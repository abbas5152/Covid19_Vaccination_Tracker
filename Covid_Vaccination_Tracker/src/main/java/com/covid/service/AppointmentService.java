package com.covid.service;

import java.util.List;
import org.springframework.stereotype.Service;
import com.covid.model.Appointment;


@Service
public interface AppointmentService {

	public List<Appointment> getAllAppointment(String key);

	public Appointment getAppointmentByBookingId(Long bookingId,String key);

	public Appointment addAppointment(Appointment app, Integer memId,String key);

	public Appointment updateAppointment(Appointment app,String key);

	public boolean deleteAppointment(Long bookingId,String key);
	
}
