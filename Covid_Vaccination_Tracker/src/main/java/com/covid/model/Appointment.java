package com.covid.model;

import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Appointment {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long bookingId;
	private Long mobileNumber;
	private LocalDate dateofBooking;
	private Slot slot;
	private Boolean bookingStatus;
	
	@OneToOne(cascade = CascadeType.ALL)
	private Member member;
	
	@OneToOne(cascade = CascadeType.ALL)
	private VaccinationCenter vaccinationCenter;
	
	
}
