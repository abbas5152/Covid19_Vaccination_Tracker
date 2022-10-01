package com.covid.model;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
<<<<<<< Updated upstream
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;
=======
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;
>>>>>>> Stashed changes

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Inventory {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer inventoryId;
	private LocalDate date;
	
	@OneToMany(cascade = CascadeType.ALL, mappedB y= "inventory")
	private List<VaccinationCenter> ListvaccinationCenter;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "inventory")
	@JsonIgnore
	private List<VaccineCount> vaccinecountList;
	
}
