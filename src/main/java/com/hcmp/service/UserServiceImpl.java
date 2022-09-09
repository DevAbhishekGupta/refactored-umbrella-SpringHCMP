package com.hcmp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hcmp.model.User;
import com.hcmp.repository.UserRepository;


@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepo;
	
	@Override
	public User addUser(User user) {
		if(user!=null)
		{
			return userRepo.saveAndFlush(user);
			
		}
		else {
			return null;
		}
			
	}
	
	@Override
	public boolean validateUserLogin(String username, String password) {
		User user = userRepo.validateUser(username, password);
		
		if(user!=null)
		{
			return true;
		}
		
		else {
			return false;
		}
	}

	@Override
	public User getUserDetails(String username) {
		
		return userRepo.getUserDetails(username);
	}
	
}
