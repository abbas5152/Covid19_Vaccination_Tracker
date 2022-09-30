package com.covid.model;

import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class CustomerDTO {

	@NotNull(message = "Please Enter Mobile Number")
	private String mobileNumber ;
	
	@NotNull(message = "Please Enter Your Password")
	private String password ;
}
