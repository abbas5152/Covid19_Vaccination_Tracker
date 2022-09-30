package com.covid.model;

import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class AdminDTO {

	  @NotNull(message = "Please Enter Your Mobile Number")
	  private String mobileNumber ;
	  
	  @NotNull(message = "Please Enter Your Password")
	  private String password ;
}
