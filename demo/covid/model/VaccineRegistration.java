package com.covid.model;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.print.attribute.standard.MediaSize.ISO;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

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
		private String mobileNumber;
	
		@JsonFormat(pattern = "dd-MM-yyyy")
//		@DateTimeFormat(iso=ISO.DESIGNATED_LONG)
		private LocalDate dateofRegistration;
		
		@OneToMany(cascade = CascadeType.ALL)
		private List<Member> memberList ;

	
		
	
}
