package com.tweetapp.tweets.service;

import java.util.List;
import java.util.Optional;

import com.tweetapp.tweets.entity.TweetEntity;
import com.tweetapp.tweets.exception.ErrorException;

public interface TweetsService {

	public List<TweetEntity> getALLTweets() throws ErrorException;

	public void deleteTweetById(int id) throws ErrorException;

	public TweetEntity saveTweetDetails(TweetEntity tweets, String username, String action, int id) throws ErrorException;

	public Optional<TweetEntity> getTweetsByUserName(String username) throws ErrorException;

}
