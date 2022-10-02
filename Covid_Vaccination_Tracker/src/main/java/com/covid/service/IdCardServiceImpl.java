package com.covid.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.covid.exception.IdCardNotFoundException;
import com.covid.exception.IdCardNotRegisterException;
import com.covid.exception.LoginException;
import com.covid.model.AdharCard;
import com.covid.model.AdminLoginSession;
import com.covid.model.CustomerLoginSession;
import com.covid.model.IdCard;
import com.covid.model.PanCard;
import com.covid.repo.AdminLoginSessionDao;
import com.covid.repo.CustomerLoginSessionDao;
import com.covid.repo.IdRepo;


@Service
public class IdCardServiceImpl implements IdService {

	@Autowired
	private IdRepo IdDao;
	
	@Autowired
	private AdminLoginSessionDao admin;
	
	@Autowired
	private CustomerLoginSessionDao cust;
	
	@Override
	public IdCard getIdcardByPanNo(String panNo,String key) throws IdCardNotFoundException {
		// TODO Auto-generated method stub
          AdminLoginSession adminLoginSession = admin.findByUuid(key);
		
		       if(adminLoginSession==null) {
				
				throw new LoginException("Unauthorised access");
			}
		
		
		
		PanCard pn=new PanCard();
		pn.setPanNumber(panNo);
		IdCard idcard = IdDao.findByPanCard(pn);
		if (idcard == null)
			throw new IdCardNotFoundException("IdCard not found with the panNo :" + panNo);
		else
			return idcard;
	}

	@Override
	public IdCard getIdCardByAdharNo(Long adharno,String key) throws IdCardNotFoundException {
		// TODO Auto-generated method stub
		 AdminLoginSession adminLoginSession = admin.findByUuid(key);
			
	       if(adminLoginSession==null) {
			
			throw new LoginException("Unauthorised access");
		}
		AdharCard adhr=new AdharCard();
		adhr.setAdhaarNo(adharno);
		//adhr.setAdhaarNo(adharno);
		IdCard idcard = IdDao.findByAdharCard(adhr);
		if (idcard == null)
			throw new IdCardNotFoundException("IdCard not found with the adharNo :" + adharno);
		else
			return idcard;
	}
	

	@Override
	public IdCard addIdCard(IdCard idCard,String key) throws IdCardNotRegisterException {
		
		 CustomerLoginSession custLoginSession = cust.findByUuid(key);
			
	       if(custLoginSession==null) {
			
			throw new LoginException("Unauthorised access");
		}
		
		IdCard id = IdDao.findByPanCard(idCard.getPanCard());
		if (id != null)
			throw new IdCardNotRegisterException("Id card already exist with the pan Number : " + idCard.getPanCard());
		IdCard id2 = IdDao.findByAdharCard(idCard.getAdharCard());
		if (id2 != null)
			throw new IdCardNotRegisterException("Id card already exist with the adhar Number : " + idCard.getAdharCard());

		return IdDao.save(idCard);
	}

}
