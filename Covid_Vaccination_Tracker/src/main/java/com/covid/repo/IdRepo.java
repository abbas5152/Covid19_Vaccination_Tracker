package com.covid.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.covid.model.AdharCard;
import com.covid.model.IdCard;
import com.covid.model.PanCard;

public interface IdRepo extends JpaRepository<IdCard,Integer> {
	
	public IdCard findByPanCard(PanCard pancard);

	public IdCard findByAdharCard(AdharCard adharcard);

}
