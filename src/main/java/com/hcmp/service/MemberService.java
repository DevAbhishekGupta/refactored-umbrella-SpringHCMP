package com.hcmp.service;

import java.util.List;

import com.hcmp.model.Member;
import com.hcmp.model.MemberDetails;

public interface MemberService {
	
	public Member addMember(Member member);
	public Member getMemberById(Integer memberId);
	public List<Member> getMemberByName(String firstName, String lastName);
	public List<Member> getMemberByPhysician(Integer physicianId);
	public Member getMemberByClaimId(Integer claimId);
	
	public List<Member> getAllMembers();
	
	public List<MemberDetails> getMemberDetailsById(Integer memberId);

}
