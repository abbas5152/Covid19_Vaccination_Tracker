  package com.covid.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class AdharCard {
		
	private Long adhaarNo;
	private String fingerPrint;
	private String irisScan;
	
}
