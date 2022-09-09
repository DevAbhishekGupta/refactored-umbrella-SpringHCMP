package com.hcmp.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hcmp.model.Member;
import com.hcmp.model.MemberDetails;
import com.hcmp.model.User;
import com.hcmp.service.MemberService;
import com.hcmp.service.PhysicianService;
import com.hcmp.service.UserService;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("mem/api")
public class MemberController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private PhysicianService physicianService;
	
	@Autowired
	private MemberService memberService;
	
	
	@PostMapping("/addMember")
	public ResponseEntity<?> addMember(@RequestBody Member member){
		System.out.println("Add Member: " + member.toString());
		
		User user = new User();
		
		user.setUsername(member.getUserName());
		user.setPassword(member.getPassWord());
		user.setRole('M');
		
		userService.addUser(user);
		
		member.setFkPhysicianId(physicianService.getRandomPhysician());
		member.setCreatedAt(LocalDate.now());
		if(memberService.addMember(member)!=null) {
			return new ResponseEntity<Member>(member, HttpStatus.CREATED);
		}
		return new ResponseEntity<String>("Member not created", HttpStatus.METHOD_FAILURE);
		
	}
	
	@GetMapping("/getMemberById/{memberId}")
	public ResponseEntity<?> getMemberById(@PathVariable("memberId") Integer memberId){
		
		if(memberId !=null && memberId > 0) {
			Member member = memberService.getMemberById(memberId);
			
			if(member != null) {
				return new ResponseEntity<Member>(member, HttpStatus.OK);
			}else {
				return new ResponseEntity<String>("Member not found", HttpStatus.NOT_FOUND);
			}
		}else {
			return new ResponseEntity<String>("Member Id can't be null", HttpStatus.NO_CONTENT);
		}
	}
	
	@GetMapping("/getMemberByName/{firstName}/{lastName}")
	public ResponseEntity<?> getMemberByName(@PathVariable("firstName") String firstName, @PathVariable("lastName") String lastName){
		
		if(firstName !=null && lastName != null) {
			List<Member> membersList = memberService.getMemberByName(firstName,lastName);
			
			if(membersList.size() > 0) {
				return new ResponseEntity<List<Member>>(membersList, HttpStatus.OK);
			}else {
				return new ResponseEntity<String>("Member not found", HttpStatus.NOT_FOUND);
			}
		}else {
			return new ResponseEntity<String>("Member Name can't be null", HttpStatus.NO_CONTENT);
		}
	}
	
	@GetMapping("/getAllMembers")
	public ResponseEntity<?> getAllMembers(){
		
		List<Member> allMembersList = memberService.getAllMembers();
		if(allMembersList.size() > 0) {
			return new ResponseEntity<List<Member>>(allMembersList, HttpStatus.OK);
		}else {
			return new ResponseEntity<String>("No Member Available", HttpStatus.NOT_FOUND);
		}
		
	}
	
	@GetMapping("/getMemberByPhysician/{physicianId}")
	public ResponseEntity<?> getMemberByName(@PathVariable("physicianId") Integer physicianId){
		
		if(physicianId !=null) {
			List<Member> membersList = memberService.getMemberByPhysician(physicianId);
			
			if(membersList.size() > 0) {
				return new ResponseEntity<List<Member>>(membersList, HttpStatus.OK);
			}else {
				return new ResponseEntity<String>("Member not found", HttpStatus.NOT_FOUND);
			}
		}else {
			return new ResponseEntity<String>("Member Name can't be null", HttpStatus.NO_CONTENT);
		}
	}
	
	@GetMapping("/getMemberDetailsById/{memberId}")
	public ResponseEntity<?> getMemberDetailsById(@PathVariable("memberId") Integer memberId){
		
		if(memberId !=null && memberId > 0) {
			List<MemberDetails> memberDetailsList = memberService.getMemberDetailsById(memberId);
			
			if(memberDetailsList.size() > 0) {
				return new ResponseEntity<List<MemberDetails>>(memberDetailsList, HttpStatus.OK);
			}else {
				return new ResponseEntity<String>("Member not found", HttpStatus.NOT_FOUND);
			}
		}else {
			return new ResponseEntity<String>("Member Id can't be null", HttpStatus.NO_CONTENT);
		}
	}

}
