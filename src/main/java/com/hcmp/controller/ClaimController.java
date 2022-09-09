package com.hcmp.controller;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hcmp.model.Claim;
import com.hcmp.model.Member;
import com.hcmp.model.User;
import com.hcmp.service.ClaimService;


@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("claim/api")
public class ClaimController {

	@Autowired
	private ClaimService claimService;
	
	@PostMapping("/addClaim")
	public ResponseEntity<?> addClaim(@RequestBody Claim claim){
		claim.setSubmittedAt(LocalDate.now());
		if(claimService.addClaim(claim)!=null) {
			return new ResponseEntity<Claim>(claim, HttpStatus.CREATED);
		}
		return new ResponseEntity<String>("Claim not created", HttpStatus.METHOD_FAILURE);
		
	}
	
}
