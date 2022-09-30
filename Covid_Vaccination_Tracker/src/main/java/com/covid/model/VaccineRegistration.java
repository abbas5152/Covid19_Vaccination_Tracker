package com.covid.model;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

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
		private Long mobileNumber;
	
		private LocalDate dateofRegistration;
		
		@OneToMany(cascade = CascadeType.ALL)
		private List<Member> memberList ;

		public Long getMobileNumber() {
			return mobileNumber;
		}

		public void setMobileNumber(Long mobileNumber) {
			this.mobileNumber = mobileNumber;
		}

		public LocalDate getDateofRegistration() {
			return dateofRegistration;
		}

		public void setDateofRegistration(LocalDate dateofRegistration) {
			this.dateofRegistration = dateofRegistration;
		}

		public List<Member> getMemberList() {
			return memberList;
		}

		public void setMemberList(List<Member> memberList) {
			this.memberList = memberList;
		}
		
	
}
