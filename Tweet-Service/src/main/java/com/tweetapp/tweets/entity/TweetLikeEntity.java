package com.tweetapp.tweets.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Document(collection = "TweetsLikeTable")
public class TweetLikeEntity {
	@Id
	public int tweetLiketId;
	public int tweetId;
	public String userName;
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
