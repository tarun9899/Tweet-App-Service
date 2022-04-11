package com.tweetapp.tweets.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "TweetsCommentTable")
public class TweetCommentEnitiy {
		@Id
		public int tweetCommentId;
		public int tweetId;
		public String userName;
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
