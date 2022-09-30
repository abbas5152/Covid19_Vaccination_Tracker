package com.covid.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
@Entity
public class Admin {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer adminId ;
	
	@NotNull(message = "Customer Name ")
	private String adminName ;
//	
//	@Size(min = 9  , max = 10 , message = "Please Enter valid mobile no ")
//	@NotNull(message = "Mobile number is mandatory")
//	@Pattern(regexp =  "^[7-9][0-9]9$")
//	@NotEmpty(message = "Mobile number is mandatory")
//	@Column(unique = true)
	private String mobileNumber;
	
//	@NotNull(message = "Password is Mandatory")
//	@Size(min = 8 ,max = 20)
	private String password ;
	
//	@Email
//	@NotNull(message = "Email is mandatory")
	private String email ;

}
