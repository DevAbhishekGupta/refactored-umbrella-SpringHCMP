package com.hcmp.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hcmp.model.User;
import com.hcmp.service.UserServiceImpl;

@CrossOrigin("*")
@RestController
@RequestMapping("user/api")
public class UserController {

	private Map<String, String> mObj = new HashMap<String, String>();

	@Autowired
	private UserServiceImpl userServiceImpl;

	@PostMapping("/registerUser")
	public ResponseEntity<?> addUser(@RequestBody User user) {
		if (userServiceImpl.addUser(user) != null) {
			return new ResponseEntity<User>(user, HttpStatus.CREATED);
		}
		return new ResponseEntity<String>("user not added", HttpStatus.CREATED);
	}


}
