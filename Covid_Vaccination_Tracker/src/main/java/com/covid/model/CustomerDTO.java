package com.covid.model;

import javax.persistence.Column;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class CustomerDTO {

	@Size(min = 9  , max = 10 , message = "Please Enter valid mobile no ")
	@NotNull(message = "Mobile number is mandatory")
	@Pattern(regexp =  "^[7-9][0-9]9$")
	@NotEmpty(message = "Mobile number is mandatory")
	@Column(unique = true)
	private String mobileNumber;
	
	@NotNull(message = "Password is Mandatory")
	@Size(min = 8 ,max = 20)
	private String password ;
}
