package com.covid.model;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Data
public class VaccineRegistration {
		
		@Id
		@Size(min = 9  , max = 10 , message = "Please Enter valid mobile no ")
		@NotNull(message = "Mobile number is mandatory")
		@Pattern(regexp =  "^[7-9][0-9]9$")
		@NotEmpty(message = "Mobile number is mandatory")
		@Column(unique = true)
		private String mobileNumber;
	
		private LocalDate dateofRegistration;
		
		@OneToMany(cascade = CascadeType.ALL)
		private List<Member> memberList ;
		
	
}
