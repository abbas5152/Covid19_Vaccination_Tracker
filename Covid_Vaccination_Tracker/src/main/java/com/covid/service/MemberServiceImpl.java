package com.covid.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.covid.exception.MemberNotFoundException;
import com.covid.exception.MemberNotRegisterException;
import com.covid.model.AdharCard;
import com.covid.model.IdCard;
import com.covid.model.Member;
import com.covid.model.PanCard;
import com.covid.model.Vaccine;
import com.covid.model.VaccineRegistration;
import com.covid.repo.IdRepo;
import com.covid.repo.MemberDao;

@Service
public class MemberServiceImpl implements MemberService {
	
	@Autowired
	MemberDao dao;

	@Autowired
	IdRepo idDao;

//	@Autowired
//	VaccineRegistrationDao vrDao;
//
//	@Autowired
//	AppointmentDao apDao;
//
//	@Autowired
//	VaccineDao vDao;
//
//	@Autowired
//	VaccineCountDao countDao;

	@Override
	public Member getMemberById(Integer idcardid) throws MemberNotFoundException {
		
		
		Optional<IdCard> idcard = idDao.findById(idcardid);
		if (idcard.isPresent()) {
			Member member = dao.findByIdCard(idcard.get());
			if (member != null) {
//				List<Appointment> appointment = apDao.findByMember(mbyId);
//				mbyId.setAppointments(appointment);
				
				return member;
			} else
				throw new MemberNotFoundException("Member not found  with this ID:" + idcardid);
		} else
			throw new MemberNotFoundException("Member not found  with this ID:" + idcardid);
		
	}

	@Override
	public Member getMemberByAdharNo(Long adharNo) throws MemberNotFoundException {
		AdharCard adhr=new AdharCard();
		adhr.setAdhaarNo(adharNo);
		IdCard idcard = idDao.findByAdharCard(adhr);
		if (idcard != null) {
			Optional<IdCard> id = idDao.findById(idcard.getId());
			Member member = dao.findByIdCard(id.get());
			if (member != null)
				return member;
			else
				throw new MemberNotFoundException("Member not available with this adhar no: " + adharNo);
		} else
			throw new MemberNotFoundException("Member not available with this adhar no: " +adharNo);
	}

	@Override
	public Member getMemberByPanNo(String panNo) throws MemberNotFoundException {
		PanCard pn=new PanCard();
		pn.setPanNumber(panNo);
		IdCard idcard = idDao.findByPanCard(pn);
		if (idcard != null) {
			Optional<IdCard> id = idDao.findById(idcard.getId());
			Member member = dao.findByIdCard(id.get());
			if (member != null)
				return member;
			else
				throw new MemberNotFoundException("Member not available with this pan no: " + panNo);
		} else
			throw new MemberNotFoundException("Member not available with this pan no: " + panNo);
	}

	

	@Override
	public Member updateMember(Member member) throws MemberNotFoundException {

		Optional<Member> mem = dao.findById(member.getMemberId());
		if (mem.isPresent()) {
			
			Member OldMember = mem.get();
			if (member.getIdCard() != null) {
				IdCard id =  OldMember.getIdCard();
				
				if (member.getIdCard().getDob() != null)
					id.setDob(member.getIdCard().getDob());
				
				if (member.getIdCard().getCity() != null)
					id.setCity(member.getIdCard().getCity());
				
				if (member.getIdCard().getGender() != null)
					id.setGender(member.getIdCard().getGender());
				
				if (member.getIdCard().getAddress() != null)
					id.setAddress(member.getIdCard().getAddress());
				
				if (member.getIdCard().getPinCode() != null)
					id.setPinCode(member.getIdCard().getPinCode());
				
				if (member.getIdCard().getState() != null)
					id.setState(member.getIdCard().getState());

				if (member.getIdCard().getAdharCard() != null) {
					AdharCard adar =  OldMember.getIdCard().getAdharCard();
					adar.setAdhaarNo(member.getIdCard().getAdharCard().getAdhaarNo());
				}

				if (member.getIdCard().getPanCard() != null) {
					PanCard pan =  OldMember.getIdCard().getPanCard();
					pan.setPanNumber(member.getIdCard().getPanCard().getPanNumber());
				}
			}
			if(member.getDose1date()!=null) 
			     OldMember.setDose1date(member.getDose1date());
			
			if(member.getDose2date()!=null) 
			     OldMember.setDose2date(member.getDose2date());
			
			if(member.getDose1status()!=null)
			     OldMember.setDose1status(member.getDose1status());
			
			if(member.getDose1status()!=null)
			     OldMember.setDose2status(member.getDose2status());
			
			if(member.getVaccine()!=null) {
				Vaccine vaccine=OldMember.getVaccine();
				vaccine.setVaccineName(member.getVaccine().getVaccineName());
				
				vaccine.setDescription(member.getVaccine().getDescription());
				
				vaccine.setVaccineCount(member.getVaccine().getVaccineCount());
			}
			  
			if(member.getVaccineRegistration()!=null) {
				VaccineRegistration vr=OldMember.getVaccineRegistration();
				
				vr.setDateofRegistration(member.getVaccineRegistration().getDateofRegistration());
				
				vr.setMobileNumber(member.getVaccineRegistration().getMobileNumber());
			}
			   
		
			     
			return dao.save(OldMember);
		} else
			throw new MemberNotFoundException("Member not available with this details :" + member);
	}

	@Override
	public boolean deleteMemberRecord(Member member) throws MemberNotFoundException {

		Optional<Member> mem = dao.findById(member.getMemberId());
		if (mem.isPresent()) {
			Member oldMember = mem.get();
			
//			if (oldMember.getVaccineRegistration() != null)
//				vrDao.delete(oldMember.getVaccineRegistration());
			
			if (oldMember.getIdCard() != null)
				idDao.delete(oldMember.getIdCard());
			
//			if (oldMember.getAppointments() != null)
//				apDao.deleteAll(exist.getAppointments());
//			
//			if (oldMember.getVaccine() != null)
//				vDao.delete(oldMember.getVaccine());
			
			dao.delete(member);
			return true;
		} else
			throw new MemberNotFoundException("Member not available with this details :" + member);
	}

	@Override
	public Member addMemberbyMobileNo(Member member, String mobileNo, String key) throws MemberNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Member> GetallTheMembers() throws MemberNotRegisterException {
		List<Member> member=	dao.findAll();
		if(member.size()>0)
			return member;
		else
			throw new MemberNotRegisterException("No member is Registered");
	}

}
