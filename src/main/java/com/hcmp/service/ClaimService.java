package com.hcmp.service;

import java.util.List;

import com.hcmp.model.Claim;
import com.hcmp.model.Member;

public interface ClaimService {

	public Claim addClaim(Claim claim);
	public Claim getClaimById(Integer claimId);
	public List<Claim> getClaimByMemberId(Integer memberId);
	
}
