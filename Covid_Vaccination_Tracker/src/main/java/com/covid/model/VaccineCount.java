package com.covid.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;



@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class VaccineCount {
		
		@Id
		@GeneratedValue(strategy = GenerationType.AUTO)
		private Integer vaccinecountId;
		private Integer quantity;
		private Double price;
		@ManyToOne(cascade = CascadeType.ALL)
		private Inventory inventory;
	
}
