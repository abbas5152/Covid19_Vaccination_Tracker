package com.covid.model;

import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
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
public class Member {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer memberId;
	private Boolean dose1status;
	private Boolean dose2status;
	private LocalDate dose1date;
	private LocalDate dose2date;
	
	@OneToOne(cascade = CascadeType.ALL)
	private IdCard idCard;
	
	@ManyToOne(cascade = CascadeType.ALL)
	private VaccineRegistration vaccineRegistration;
	
	@OneToOne(cascade = CascadeType.ALL)
	private Vaccine vaccine;
	
	@OneToOne(cascade = CascadeType.ALL)
	private Appointment appointment;
	
	
	
}
