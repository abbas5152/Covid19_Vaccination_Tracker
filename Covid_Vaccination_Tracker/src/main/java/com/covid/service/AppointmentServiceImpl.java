//package com.covid.service;
//
//import java.util.List;
//import java.util.Optional;
//
//import org.springframework.beans.factory.annotation.Autowired;
//
//import com.covid.exception.AppointmentException;
//import com.covid.model.AdminLoginSession;
//import com.covid.model.Appointment;
//import com.covid.model.CustomerLoginSession;
//import com.covid.repo.AdminLoginSessionDao;
//import com.covid.repo.AppointmentDao;
//import com.covid.repo.CustomerLoginSessionDao;
//
//public class AppointmentServiceImpl implements AppointmentService{
//
//	
//	@Autowired
//	private AppointmentDao appointmentDao;
//	
//	@Autowired
//	private AdminLoginSessionDao adminLoginSessionDao ;
//	
//	@Autowired
//	private CustomerLoginSessionDao customerLoginSessionDao ;
//	
//	@Override
//	public List<Appointment> getAllAppointment(String key) {
//		 AdminLoginSession adminLoginSession = adminLoginSessionDao.findByUuid(key);
//			
//			if(adminLoginSession ==null  ) {
//				
//				throw new AppointmentException("Unauthorised access");
//			}
//			
//		List<Appointment> appointments = appointmentDao.findAll();
//		if (appointments.size() > 0)
//			return appointments;
//		else
//			throw new AppointmentException("No appointment found");
//	}
//
//	@Override
//	public Appointment getAppointmentByBookingId(Long bookingId, String key) {
//		AdminLoginSession adminLoginSession = adminLoginSessionDao.findByUuid(key);
//		CustomerLoginSession customerLoginSession = customerLoginSessionDao.findByUuid(key);
//			
//			if(adminLoginSession==null && customerLoginSession==null) {
//				
//				throw new AppointmentException("Unauthorised access");
//			}
//			
//			
//		Optional<Appointment> opt =  appointmentDao.findById(bookingId) ;
//		if(opt.isPresent())
//			return opt.get();
//		else
//			throw new AppointmentException("Appointment not found by same booking id!");
//				
//	}
//
//	@Override
//	public Appointment addAppointment(Appointment app, Integer memId, String key) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public Appointment updateAppointment(Appointment app, String key) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public boolean deleteAppointment(Long bookingId, String key) {
//		// TODO Auto-generated method stub
//		return false;
//	}
//
//	
//}
