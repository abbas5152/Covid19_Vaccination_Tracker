package com.covid.service;

import com.covid.exception.IdCardNotFoundException;
import com.covid.exception.IdCardNotRegisterException;
import com.covid.model.IdCard;

public interface IdService {
	

	public IdCard getIdcardByPanNo(String panNumber) throws IdCardNotFoundException;

	public IdCard getIdCardByAdharNo(Long adharnumber) throws IdCardNotFoundException;

	public IdCard addIdCard(IdCard idCard) throws IdCardNotRegisterException;

}
