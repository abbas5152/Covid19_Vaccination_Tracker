package com.covid.service;

import com.covid.exception.IdCardNotFoundException;
import com.covid.exception.IdCardNotRegisterException;
import com.covid.model.IdCard;

public interface IdService {
	

	public IdCard getIdcardByPanNo(String panNumber,String key) throws IdCardNotFoundException;

	public IdCard getIdCardByAdharNo(Long adharnumber,String key) throws IdCardNotFoundException;

	public IdCard addIdCard(IdCard idCard,String key) throws IdCardNotRegisterException;

}
