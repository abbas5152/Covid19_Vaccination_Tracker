  package com.covid.model;

import java.util.Objects;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter
public class AdharCard {
		
	private Long adhaarNo;
//	private String fingerPrint;
//	private String irisScan;
	public Long getAdhaarNo() {
		return adhaarNo;
	}
	public void setAdhaarNo(Long adhaarNo) {
		this.adhaarNo = adhaarNo;
	}
//	public String getFingerPrint() {
//		return fingerPrint;
//	}
//	public void setFingerPrint(String fingerPrint) {
//		this.fingerPrint = fingerPrint;
//	}
//	public String getIrisScan() {
//		return irisScan;
//	}
//	public void setIrisScan(String irisScan) {
//		this.irisScan = irisScan;
//	}
	@Override
	public int hashCode() {
		return Objects.hash(adhaarNo);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AdharCard other = (AdharCard) obj;
		return Objects.equals(adhaarNo, other.adhaarNo);
	}
	public AdharCard(Long adhaarNo) {
		
		this.adhaarNo = adhaarNo;
	}
	public AdharCard() {
		// TODO Auto-generated constructor stub
	}
	
	
	
}
