package com.covid.model;

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
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Data
public class Vaccine {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer vaccineId;
	private String vaccineName;
	private String description;
	
	@OneToOne(cascade = CascadeType.ALL)
	private Member member;
	
	@OneToOne(cascade = CascadeType.ALL)
	private VaccineCount vaccineCount;
	
	
}
