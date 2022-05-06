package com.tweetapp.tweets.entity;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

@DynamoDBTable(tableName = "TweetCommentTable")
public class TweetCommentEnitiy {
	    @DynamoDBHashKey
		public int tweetCommentId;
	    @DynamoDBAttribute
		public int tweetId;
	    @DynamoDBAttribute
		public String userName;
	    @DynamoDBAttribute
		public String comment;
	    
		public int getTweetCommentId() {
			return tweetCommentId;
		}
		public void setTweetCommentId(int tweetCommentId) {
			this.tweetCommentId = tweetCommentId;
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
		public String getComment() {
			return comment;
		}
		public void setComment(String comment) {
			this.comment = comment;
		}
		
		
}
