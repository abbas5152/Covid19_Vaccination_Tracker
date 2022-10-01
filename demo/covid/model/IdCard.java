package com.covid.model;

import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter
public class IdCard {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	

	@NotNull(message = "Id name can not be Null.Please Add Proper Name")
	@NotBlank(message = "Id name can not be Blank.Name is Mandotory")
	private String name;
	
	@NotNull(message = "Id Gender can not be Null.Please Add Proper Gender")
	@Pattern(regexp = "^[A-Z][a-z]*")
	private String gender;
	
	@NotNull(message = "Id Address can not be Null.Please Add Proper Address")
	private String address;
	
	@NotNull(message = "Id city can not be Null.Please Add Proper city")
	private String city;
	
	@NotNull(message = "Id state can not be Null.Please Add Proper state")
	private String state;
	
	@NotNull(message = "Id pincode can not be Null.Please Add Proper pincode")
	@Size(min = 6, max = 6)
	private String pinCode;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
	private LocalDate dob;
	
	@Embedded
	private PanCard panCard;
	
	@Embedded
	private AdharCard adharCard;
	
	
	@OneToOne(cascade = CascadeType.ALL)
	@JsonIgnore
	private Member member;

	
	
	
}
