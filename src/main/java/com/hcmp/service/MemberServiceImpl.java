package com.hcmp.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hcmp.model.Claim;
import com.hcmp.model.Member;
import com.hcmp.model.MemberDetails;
import com.hcmp.repository.MemberRepository;

@Service
public class MemberServiceImpl implements MemberService {

	@Autowired
	private MemberRepository memberRepository;

	@Autowired
	private ClaimService claimService;

	@Autowired
	private PhysicianService physicianService;

	@Override
	public Member addMember(Member member) {

		if (member != null) {
			return memberRepository.saveAndFlush(member);
		} else {
			return null;
		}

	}

	@Override
	public Member getMemberById(Integer memberId) {

		Member member = memberRepository.findById(memberId).get();

		if (member != null) {
			return member;
		}
		return null;
	}

	@Override
	public List<Member> getMemberByName(String firstName, String lastName) {
		List<Member> membersList = memberRepository.getMemberByName(firstName, lastName);
		if (membersList.size() > 0) {
			return membersList;
		}
		return null;
	}

	@Override
	public List<Member> getMemberByPhysician(Integer physicianId) {
		List<Member> membersList = memberRepository.getMemberByPhysician(physicianId);
		if (membersList.size() > 0) {
			return membersList;
		}
		return null;
	}

	@Override
	public Member getMemberByClaimId(Integer claimId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Member> getAllMembers() {

		List<Member> allMembersList = memberRepository.findAll();

		if (allMembersList.size() > 0) {
			return allMembersList;
		}

		return null;

	}

	@Override
	public List<MemberDetails> getMemberDetailsById(Integer memberId) {

		Member member = memberRepository.findById(memberId).get();
		List<MemberDetails> memberDetailsList = new ArrayList<>();

		MemberDetails memberDetails = new MemberDetails();
		String physicianName;

		if (member != null) {
			physicianName = physicianService.getPhysicianName(member.getFkPhysicianId());
			
			memberDetails.setMemberId(memberId);
			memberDetails.setFirstName(member.getFirstName());
			memberDetails.setLastName(member.getLastName());
			memberDetails.setPhysician(physicianName);

			List<Claim> claimList = claimService.getClaimByMemberId(memberId);

			if (claimList.size() > 0) {

				for (Claim claim : claimList) {
					MemberDetails memberDetail = new MemberDetails();
					memberDetail.setMemberId(memberId);
					memberDetail.setFirstName(member.getFirstName());
					memberDetail.setLastName(member.getLastName());
					memberDetail.setPhysician(physicianName);
					memberDetail.setClaimId(claim.getClaimId());
					memberDetail.setClaimAmount(claim.getClaimAmount());
					memberDetail.setSubmittedDate(claim.getClaimDate());
					
					memberDetailsList.add(memberDetail);
				}
				
				return memberDetailsList;

			} else {
				memberDetailsList.add(memberDetails);
				return memberDetailsList;
			}

		}
		return null;
	}

}
