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
	@NotNull(message = "Center name can not be null")
	@NotBlank(message = "Center name is Mandatory")
	private String centername;

	@NotNull(message = "City can not be null")
	@NotBlank(message = "City name is Mandatory")
	private String city;

	@NotNull(message = "Address can not be null")
	@NotBlank(message = "Address is Mandatory")
	private String address;

	@NotNull(message = "State can not be null")
	@NotBlank(message = "State name is Mandatory")
	private String state;

	@NotNull(message = "Pincode can not be null")
	@NotBlank(message = "Pincode is Mandatory")
	@Size(min = 6, max = 8)
	private String pincode;
	
	@JsonIgnore
	@ManyToOne(cascade = CascadeType.ALL)
	private Inventory inventory;
	
	@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "vaccinationCenter")
	private List<Appointment> appointments;
	
}
