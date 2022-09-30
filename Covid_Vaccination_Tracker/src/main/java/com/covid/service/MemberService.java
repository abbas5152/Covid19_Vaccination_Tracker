package com.covid.service;

import java.util.List;

import com.covid.exception.MemberNotFoundException;
import com.covid.exception.MemberNotRegisterException;
import com.covid.model.Member;

public interface MemberService {
	
    
	public List<Member> GetallTheMembers() throws MemberNotRegisterException;
     
	public Member getMemberById(Integer idcardid) throws MemberNotFoundException;

	public Member getMemberByAdharNo(Long adharNo) throws MemberNotFoundException;

	public Member getMemberByPanNo(String panNo) throws MemberNotFoundException;
	
	public Member addMemberbyMobileNo(Member member, String mobileNo) throws MemberNotFoundException;

	public Member updateMember(Member member) throws MemberNotFoundException;

	public boolean deleteMemberRecord(Member member) throws MemberNotFoundException;

	

}
