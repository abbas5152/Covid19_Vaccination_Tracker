package com.covid.service;


import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.covid.exception.AppointmentException;
import com.covid.exception.LoginException;
import com.covid.exception.MemberNotFoundException;
import com.covid.exception.VaccineRegistrationException;
import com.covid.model.AdminLoginSession;
import com.covid.model.Appointment;
import com.covid.model.CustomerLoginSession;
import com.covid.model.Member;
import com.covid.model.VaccinationCenter;
import com.covid.model.VaccineRegistration;
import com.covid.repo.AdminLoginSessionDao;
import com.covid.repo.AppointmentDao;
import com.covid.repo.CustomerLoginSessionDao;


public class AppointmentServiceImpl implements AppointmentService{

	
	@Autowired
	private AppointmentDao appointmentDao;
	
	@Autowired
	private AdminLoginSessionDao adminLoginSessionDao ;
	
	@Autowired
	private CustomerLoginSessionDao customerLoginSessionDao ;
	
	@Autowired
	private VaccineRegistrationService registrationService;

	@Autowired
	private MemberService memberService;

	@Autowired
	private VaccinationCenterService vaccinationCenterService;
	
	
	
	
	
	@Override
	public List<Appointment> getAllAppointment(String key) throws AppointmentException {
		 AdminLoginSession adminLoginSession = adminLoginSessionDao.findByUuid(key);
			
			if(adminLoginSession ==null  ) {
				
				throw new RuntimeException("Unauthorised access");
			}
			
		List<Appointment> appointments = appointmentDao.findAll();
		if (appointments.size() > 0)
			return appointments;
		else
			throw new AppointmentException("No appointment found");
	}

	@Override
	public Appointment getAppointmentByBookingId(Long bookingId, String key) throws AppointmentException {
		
		AdminLoginSession adminLoginSession = adminLoginSessionDao.findByUuid(key);
		
		CustomerLoginSession customerLoginSession = customerLoginSessionDao.findByUuid(key);
			
			if(adminLoginSession==null && customerLoginSession==null) {
				
				throw new RuntimeException("Unauthorised access");
			}
			
			
		Optional<Appointment> opt =  appointmentDao.findByBookingId(bookingId) ;
		if(opt.isPresent())
			return opt.get();
		else
			throw new AppointmentException("Appointment not found by same booking id!");
				
	}
	
	
	

	@Override
	public Appointment addAppointment(Appointment app, Integer memId, String key) throws VaccineRegistrationException, MemberNotFoundException, AppointmentException {
		CustomerLoginSession customerLoginSession = customerLoginSessionDao.findByUuid(key);
			
			if(customerLoginSession==null) {
				
				throw new LoginException("Unauthorised access");
			}
	

		VaccineRegistration reg = registrationService.getVaccineRegistration(app.getMobileNumber(), key);
		
		
		if (reg == null)
			throw new RuntimeException("First do the registration...");
		else {
			
			List<Member> list = reg.getMemberList();
			
			for (Member m : list) {
				if (m.getMemberId() == memId) {
					app.setMember(m);
				 app.setDateofBooking(LocalDate.now());
					app.setBookingStatus(true);
					
					Integer id = app.getVaccinationCenter().getCenterId();
					
					
					VaccinationCenter vaccinationCenter = vaccinationCenterService.getVaccineCenter(id,key);
					
					
					app.setVaccinationCenter(vaccinationCenter);
					
					
					Appointment a = appointmentDao.save(app);
					m.getListofappointments().add(a);
					memberService.updateMember(m, key) ;
					
					return a;
				}
			}
			throw new AppointmentException("Member not found...");
		}
	}

	@Override
	public Appointment updateAppointment(Appointment app, String key) throws AppointmentException {
    AdminLoginSession adminLoginSession = adminLoginSessionDao.findByUuid(key);
		
		CustomerLoginSession customerLoginSession = customerLoginSessionDao.findByUuid(key);
			
			if(adminLoginSession==null && customerLoginSession==null) {
				
				throw new RuntimeException("Unauthorised access");
			}
			
			Appointment appointment = appointmentDao.findByBookingId(app.getBookingId())
					.orElseThrow(() -> new AppointmentException("Appointment not found!"));

			appointment.setDateofBooking(app.getDateofBooking());
			appointment.setVaccinationCenter(app.getVaccinationCenter());
			appointment.setSlot(app.getSlot());
			return appointmentDao.save(appointment);
	}

	@Override
	public boolean deleteAppointment(Long bookingId, String key) throws AppointmentException {
		 AdminLoginSession adminLoginSession = adminLoginSessionDao.findByUuid(key);
			
			CustomerLoginSession customerLoginSession = customerLoginSessionDao.findByUuid(key);
				
				if(adminLoginSession==null && customerLoginSession==null) {
					
					throw new RuntimeException("Unauthorised access");
				}
				
				
				Appointment ExitApp = appointmentDao.findByBookingId(bookingId)
						.orElseThrow(() -> new AppointmentException("Appointment not found!"));
				appointmentDao.delete(ExitApp);
				return true;
	}

	
}
