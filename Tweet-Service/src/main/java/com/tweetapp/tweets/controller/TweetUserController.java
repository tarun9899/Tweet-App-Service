package com.tweetapp.tweets.controller;


import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tweetapp.tweets.DTO.MessageDTO;

import com.tweetapp.tweets.common.MessageServiceResponse;
import com.tweetapp.tweets.constants.MessageConstants;

import com.tweetapp.tweets.entity.UserEntity;
import com.tweetapp.tweets.exception.ErrorException;
import com.tweetapp.tweets.service.TweetUserService;

@RestController
@RequestMapping("/api/v1.0/tweets")

public class TweetUserController {

	@Autowired
	public TweetUserService userService;

	@Autowired
	public MessageServiceResponse messageService;

	@CrossOrigin(origins="http://localhost:4200")
	@PostMapping(value = "/register")
	public ResponseEntity<MessageDTO> userRegistrationService(@RequestBody UserEntity user) throws ErrorException {
		try {
			MessageDTO message = userService.saveUserRegisterDetails(user);
			return ResponseEntity.ok().body(message);
		} catch (Exception e) {
			throw new ErrorException(e.getMessage(), MessageConstants.SAVE_SERVICE_ERROR,
					MessageConstants.USER_REGISTRATION_SERVICE_METHOD_NAME);
		}
	}

	@CrossOrigin(origins="http://localhost:4200")
	@PostMapping(value = "/login")
	public ResponseEntity<MessageDTO> userLoggedInService(@RequestBody UserEntity user) throws ErrorException {
		try {
			MessageDTO message = userService.getLoginUserDetails(user);
			return ResponseEntity.ok().body(message);
		} catch (Exception e) {
			throw new ErrorException(e.getMessage(), MessageConstants.SAVE_SERVICE_ERROR,
					MessageConstants.USER_LOGGEDIN_SERVICE_METHOD_NAME);
		}
	}

	@CrossOrigin(origins="http://localhost:4200")
	@PutMapping(value = "/{username}/forgot")
	public ResponseEntity<MessageDTO> userResetPasswordService(@RequestBody UserEntity password,
			@PathVariable String username) throws ErrorException {
		try {
			MessageDTO message = userService.saveUserResetPassworDetails(password, username);
			return ResponseEntity.ok().body(message);
		} catch (Exception e) {
			throw new ErrorException(e.getMessage(), MessageConstants.SAVE_SERVICE_ERROR,
					MessageConstants.USER_RESET_PASSWORD_SERVICE_METHOD_NAME);
		}
	}

	@CrossOrigin(origins="http://localhost:4200")
	@GetMapping(value = "/users/all")
	public ResponseEntity<?> getAllRegistartionUser() throws ErrorException {
		try {
			return ResponseEntity.ok().body(userService.getALLRegisteredUser());
		} catch (Exception e) {
			throw new ErrorException(e.getMessage(), MessageConstants.FETCH_SERVICE_ERROR,
					MessageConstants.GET_ALL_REGISTARTION_USER_METHOD_NAME);
		}
	}

	@CrossOrigin(origins="http://localhost:4200")
	@GetMapping(value = "/users/search/{username}")
	public ResponseEntity<Optional<UserEntity>> getUserByUserName(@PathVariable String username) throws ErrorException {
		
			Optional<UserEntity> users = userService.getUserDetailsByName(username);
			return ResponseEntity.ok().body(users);
			
		
	}
}
