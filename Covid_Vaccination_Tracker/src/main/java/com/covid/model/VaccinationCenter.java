package com.covid.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class VaccinationCenter {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer centerId;
	private String centerName;
	private String address;
	private String city;
	private String state;
	private String pinCode;
	
	@JsonIgnore
	@ManyToOne(cascade = CascadeType.ALL)
	private Inventory inventory;
	
	
}
