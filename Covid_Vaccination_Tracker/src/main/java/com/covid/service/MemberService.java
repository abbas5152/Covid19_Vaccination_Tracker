package com.covid.service;

import java.util.List;

import com.covid.exception.MemberNotFoundException;
import com.covid.exception.MemberNotRegisterException;
import com.covid.model.Member;

public interface MemberService {
	
    
	public List<Member> GetallTheMembers(String key) throws MemberNotRegisterException;
     
	public Member getMemberById(Integer idcardid,String key) throws MemberNotFoundException;

	public Member getMemberByAdharNo(Long adharNo,String key) throws MemberNotFoundException;

	public Member getMemberByPanNo(String panNo,String key) throws MemberNotFoundException;
	
	public Member addMemberbyMobileNo(Member member, String mobileNo,String key) throws MemberNotFoundException;

	public Member updateMember(Member member,String key) throws MemberNotFoundException;

	public boolean deleteMemberRecord(Member member,String key) throws MemberNotFoundException;

	

}
