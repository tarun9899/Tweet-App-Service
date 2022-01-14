package com.tweetapp.tweets.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "TweetsTable")
public class TweetEntity {
	@Id
	public int tweetId;
	public int userId;
	public boolean tweetLikes;
	public String tweets;
	public String replyTweets;
	public int getTweetId() {
		return tweetId;
	}
	public void setTweetId(int tweetId) {
		this.tweetId = tweetId;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public boolean getTweetLikes() {
		return tweetLikes;
	}
	public void setTweetLikes(boolean tweetLikes) {
		this.tweetLikes = tweetLikes;
	}
	public String getTweets() {
		return tweets;
	}
	public void setTweets(String tweets) {
		this.tweets = tweets;
	}
	public String getReplyTweets() {
		return replyTweets;
	}
	public void setReplyTweets(String replyTweets) {
		this.replyTweets = replyTweets;
	}
	
    
}
