package com.covid.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.covid.exception.IdCardNotFoundException;
import com.covid.exception.IdCardNotRegisterException;
import com.covid.exception.MemberNotFoundException;
import com.covid.exception.MemberNotRegisterException;
import com.covid.model.IdCard;
import com.covid.model.Member;
import com.covid.service.IdService;
import com.covid.service.MemberService;

@RestController
public class controller {
	
	@Autowired
	private IdService idservice;
	
	@Autowired
	private MemberService memberservice;
	
	//for id
	@PostMapping("/Id")
	public ResponseEntity<IdCard> AddIdCardHandler(@RequestBody IdCard idcard,@RequestParam String key) throws IdCardNotRegisterException{
		
		IdCard idc=idservice.addIdCard(idcard, key);
		
		return new ResponseEntity<IdCard>(idc,HttpStatus.CREATED);
	}
	
	@GetMapping("/IdByAdhar/{AdharNo}")
	public ResponseEntity<IdCard> FindByAdharHandler(@PathVariable Long AdharNo,@RequestParam String key) throws IdCardNotFoundException{
		
		IdCard idc=idservice.getIdCardByAdharNo(AdharNo, key);
		return new ResponseEntity<IdCard>(idc,HttpStatus.FOUND);
		
	}
	
	@GetMapping("/IdByPan/{PanNo}")
	public ResponseEntity<IdCard> FindByPanHandler(@PathVariable String PanNo,@RequestParam String key) throws IdCardNotFoundException{
		
		IdCard idc=idservice.getIdcardByPanNo(PanNo, key);
		return new ResponseEntity<IdCard>(idc,HttpStatus.FOUND);
		
	}
	
	//for member
	
	@GetMapping("/MemberById/{id}")
	public ResponseEntity<Member> FindByIdHandler(@PathVariable Integer id,@RequestParam String key) throws MemberNotFoundException{
		
	Member member=	memberservice.getMemberById(id, key);
	return new ResponseEntity<Member>(member,HttpStatus.FOUND);
	
		
	}
	
	@GetMapping("/MemberByPan/{PanNo}")
	public ResponseEntity<Member> FindByPanMemberHandler(@PathVariable String PanNo,@RequestParam String key) throws MemberNotFoundException{
		
	Member member=	memberservice.getMemberByPanNo(PanNo, key);
	return new ResponseEntity<Member>(member,HttpStatus.FOUND);
	
		
	}
	@GetMapping("/MemberByAdhar/{AdharNo}")
	public ResponseEntity<Member> FindByAdharMemberHandler(@PathVariable Long AdharNo,@RequestParam String key) throws MemberNotFoundException{
		
	Member member=	memberservice.getMemberByAdharNo(AdharNo, key);
	return new ResponseEntity<Member>(member,HttpStatus.FOUND);
	
		
	}
	@GetMapping("/Members")
	public ResponseEntity<List<Member>> FindAllMembersHandler(@RequestParam String key) throws MemberNotRegisterException{
		List<Member> members=memberservice.GetallTheMembers(key);
		
		return new ResponseEntity<List<Member>>(members,HttpStatus.FOUND);
	}
	@DeleteMapping("/Member")
	public ResponseEntity<Boolean> DeleteMemberHandler(@RequestBody Member member,@RequestParam String key) throws MemberNotFoundException{
		Boolean ans=memberservice.deleteMemberRecord(member, key);
		return new ResponseEntity<Boolean>(ans,HttpStatus.ACCEPTED);
		
	}

	@PutMapping("/Member")
	public ResponseEntity<Member> UpdateMemberHandler(@RequestBody Member member,@RequestParam String key) throws MemberNotFoundException{
		Member memb=memberservice.updateMember(member, key);
		
		return new ResponseEntity<Member>(member,HttpStatus.FOUND);
	}
	@PostMapping("/Member/{Number}")
	public ResponseEntity<Member> AddMemberHandler(@RequestBody Member member,@PathVariable String Number,@RequestParam String key) throws MemberNotFoundException{
		
		Member m=memberservice.addMemberbyMobileNo(member, Number, key);
		
		return new ResponseEntity<Member>(m,HttpStatus.CREATED);
	}
}
