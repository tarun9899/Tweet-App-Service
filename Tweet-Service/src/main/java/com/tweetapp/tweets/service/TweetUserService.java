package com.tweetapp.tweets.service;

import java.util.List;
import java.util.Optional;

import com.tweetapp.tweets.DTO.MessageDTO;
import com.tweetapp.tweets.entity.UserEntity;
import com.tweetapp.tweets.exception.ErrorException;

public interface TweetUserService {

	public MessageDTO saveUserRegisterDetails(UserEntity user) throws ErrorException;

	public List<UserEntity> getALLRegisteredUser() throws ErrorException;

	public MessageDTO saveUserResetPassworDetails(UserEntity password, String username) throws ErrorException;

	public MessageDTO getLoginUserDetails(UserEntity user) throws ErrorException;

	public UserEntity getUserDetailsByName(String username) throws ErrorException;
}
