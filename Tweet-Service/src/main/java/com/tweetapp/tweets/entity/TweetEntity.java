package com.tweetapp.tweets.entity;

import java.util.Optional;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "TweetsTable")
public class TweetEntity {
	@Id
	public int tweetId;
	public int userId;
	public String tweets;
	public String userName;
	public String replyTweets;
    public int likeTweetsCount;
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
