package com.hcmp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hcmp.model.Claim;
import com.hcmp.model.Member;
import com.hcmp.repository.ClaimRepository;

@Service
public class ClaimServiceImpl implements ClaimService {

	@Autowired
	private ClaimRepository claimRepository;
	
	@Override
	public Claim addClaim(Claim claim) {
		if(claim != null) {
			return claimRepository.saveAndFlush(claim);
		}else {
			return null;
		}
	}

	@Override
	public Claim getClaimById(Integer claimId) {
		
		Claim claim = claimRepository.findById(claimId).get();

		if(claim != null) {
			return claim;
		}
		return null;
	}

	@Override
	public List<Claim> getClaimByMemberId(Integer memberId) {
		List<Claim> claimList = claimRepository.getClaimByMemberId(memberId);
		
		if(claimList.size() > 0) {
			return claimList;
		}
		return null;
	}

}
