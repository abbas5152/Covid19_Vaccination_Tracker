package com.covid.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.covid.exception.IdCardNotFoundException;
import com.covid.exception.IdCardNotRegisterException;
import com.covid.model.AdharCard;
import com.covid.model.IdCard;
import com.covid.model.PanCard;
import com.covid.repo.IdRepo;


@Service
public class IdCardServiceImpl implements IdService {

	@Autowired
	private IdRepo IdDao;
	
	@Override
	public IdCard getIdcardByPanNo(String panNo) throws IdCardNotFoundException {
		// TODO Auto-generated method stub
		PanCard pn=new PanCard();
		pn.setPanNumber(panNo);
		IdCard idcard = IdDao.findByPanCard(pn);
		if (idcard == null)
			throw new IdCardNotFoundException("IdCard not found with the panNo :" + panNo);
		else
			return idcard;
	}

	@Override
	public IdCard getIdCardByAdharNo(Long adharno) throws IdCardNotFoundException {
		// TODO Auto-generated method stub
		AdharCard adhr=new AdharCard(adharno);
		//adhr.setAdhaarNo(adharno);
		IdCard idcard = IdDao.findByAdharCard(adhr);
		if (idcard == null)
			throw new IdCardNotFoundException("IdCard not found with the adharNo :" + adharno);
		else
			return idcard;
	}

	@Override
	public IdCard addIdCard(IdCard idCard) throws IdCardNotRegisterException {
		
		IdCard id = IdDao.findByPanCard(idCard.getPanCard());
		if (id != null)
			throw new IdCardNotRegisterException("Id card already exist with the pan Number : " + idCard.getPanCard());
		IdCard id2 = IdDao.findByAdharCard(idCard.getAdharCard());
		if (id2 != null)
			throw new IdCardNotRegisterException("Id card already exist with the adhar Number : " + idCard.getAdharCard());

		return IdDao.save(idCard);
	}

}
