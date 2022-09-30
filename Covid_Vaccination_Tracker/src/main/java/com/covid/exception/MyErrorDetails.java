package com.covid.exception;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class MyErrorDetails {
	
	private LocalDateTime timestap;
	private String message;
	private String details;
	

	   
	  

}
