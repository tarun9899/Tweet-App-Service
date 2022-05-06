package com.tweetapp.tweets.entity;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

@DynamoDBTable(tableName = "TweetLikeTable")
public class TweetLikeEntity {
	@DynamoDBHashKey
	public int tweetLiketId;
	@DynamoDBAttribute
	public int tweetId;
	@DynamoDBAttribute
	public String userName;
	@DynamoDBAttribute
	public boolean tweetLikes;
	
	public int getTweetLiketId() {
		return tweetLiketId;
	}
	public void setTweetLiketId(int tweetLiketId) {
		this.tweetLiketId = tweetLiketId;
	}
	public boolean isTweetLikes() {
		return tweetLikes;
	}
	public void setTweetLikes(boolean tweetLikes) {
		this.tweetLikes = tweetLikes;
	}
	public int getTweetId() {
		return tweetId;
	}
	public void setTweetId(int tweetId) {
		this.tweetId = tweetId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
}
