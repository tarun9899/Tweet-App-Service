package com.tweetapp.tweets.DAO;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Repository;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.tweetapp.tweets.entity.UserEntity;

@Repository
public class TweetUserRepository  {
	
	
	@Autowired
	private DynamoDBMapper  dynamoDBMapper;
	
	
	public UserEntity userSaveDetails(UserEntity userObject) {
		 dynamoDBMapper.save(userObject);
		 return userObject;
	}
	
	public UserEntity userFindByIdDetails(UserEntity user) {
	return  dynamoDBMapper.load(UserEntity.class,user.getUserId());
	}
	
	public List<UserEntity> userFindALLDetails() {
		return  dynamoDBMapper.scan(UserEntity.class, new DynamoDBScanExpression());
   }
}
