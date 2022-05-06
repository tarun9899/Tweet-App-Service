package com.tweetapp.tweets.entity;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

@DynamoDBTable(tableName = "TweetTable")
public class TweetEntity {
	@DynamoDBHashKey
	public int tweetId;
	@DynamoDBAttribute
	public int userId;
	@DynamoDBAttribute
	public String tweets;
	@DynamoDBAttribute
	public String userName;
	@DynamoDBAttribute
	public String replyTweets;
	@DynamoDBAttribute
    public int likeTweetsCount;
	@DynamoDBAttribute
    public int commentTweetsCount;
	
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

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public int getLikeTweetsCount() {
		return likeTweetsCount;
	}

	public void setLikeTweetsCount(int tweetLikeCount) {
		this.likeTweetsCount = tweetLikeCount;
	}

	public int getCommentTweetsCount() {
		return commentTweetsCount;
	}

	public void setCommentTweetsCount(int commentTweetsCount) {
		this.commentTweetsCount = commentTweetsCount;
	}
	
}
