package com.tweetapp.tweets.service;

import java.util.List;


import com.tweetapp.tweets.entity.TweetCommentEnitiy;
import com.tweetapp.tweets.entity.TweetEntity;
import com.tweetapp.tweets.entity.TweetLikeEntity;
import com.tweetapp.tweets.exception.ErrorException;

public interface TweetsService {

	public List<TweetEntity> getALLTweets() throws ErrorException;

	public void deleteTweetById(int id,String tweet) throws ErrorException;

	public TweetEntity saveTweetDetails(TweetEntity tweets, String username, String action, int id) throws ErrorException;

	public List<TweetEntity> getTweetsByUserName(String username) throws ErrorException;

	public TweetLikeEntity saveLikeTweetDetails(TweetLikeEntity tweetsLikes, String username, int id) throws ErrorException;

	public TweetCommentEnitiy saveCommentTweetDetails(TweetCommentEnitiy tweets, String username,
			int id);

	public List<TweetLikeEntity> getALLLikeTweets() throws ErrorException;

	List<TweetCommentEnitiy> getALLCommentsTweets() throws ErrorException;

}
