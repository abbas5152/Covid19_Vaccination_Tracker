package com.covid.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@NoArgsConstructor
@Data
@ToString
public class AdminLoginSession {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer adminLoginId ;
	
	@NotNull(message = "Please Enter admin Id")
	private Integer adminId ;
	
	@Column(unique = true)
	private String uuid ;
	
	private LocalDateTime dateTime ;
}
