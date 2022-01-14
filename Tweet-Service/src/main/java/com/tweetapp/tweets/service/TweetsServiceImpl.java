package com.tweetapp.tweets.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;


import com.tweetapp.tweets.DAO.TweetUserRepository;
import com.tweetapp.tweets.DAO.TweetsRespository;
import com.tweetapp.tweets.common.MessageServiceResponse;
import com.tweetapp.tweets.constants.MessageConstants;

import com.tweetapp.tweets.entity.TweetEntity;
import com.tweetapp.tweets.entity.UserEntity;
import com.tweetapp.tweets.exception.ErrorException;

@Service
public class TweetsServiceImpl implements TweetsService {

	@Autowired
	public TweetsRespository tweetsRepository;

	@Autowired
	public TweetUserRepository userRepository;

	@Autowired
	public TweetUserService tweetUserService;

	@Autowired
	public MessageServiceResponse messageService;

	
	@Override
	public List<TweetEntity> getALLTweets() throws ErrorException {
		try {
			return tweetsRepository.findAll();
		} catch (Exception e) {
			throw new ErrorException(e.getMessage(), MessageConstants.FETCH_SERVICE_ERROR,
					MessageConstants.GET_ALL_TWEETS);
		}

	}
	
	@Override
	public Optional<TweetEntity> getTweetsByUserName(String username) throws ErrorException {

		try {
			List<UserEntity> userList = tweetUserService.getALLRegisteredUser();
			for (UserEntity users : userList) {
				if (users.getUserName().equals(username)) {
					System.out.println("id - " + users.getUserId());
					return tweetsRepository.findById(users.getUserId());
				}
			}
		} catch (Exception e) {
			throw new ErrorException(e.getMessage(), MessageConstants.FETCH_SERVICE_ERROR,
					MessageConstants.GET_ALL_TWEETS_BY_USER_NAME);
		}
		return null;
	}

	

	@Override
	public TweetEntity saveTweetDetails(TweetEntity tweets, String username, String action, int id)
			throws ErrorException {
		try {
			int tweetUpdatedId = 0;
			switch (action) {
			case MessageConstants.ADD_TWEETS:
				List<TweetEntity> tweetlist = tweetsRepository.findAll();
				for (TweetEntity tweetValue : tweetlist) {
					tweetUpdatedId = tweetValue.getTweetId();
					System.out.println("ID -" + tweetUpdatedId);
				}
				List<UserEntity> users = tweetUserService.getALLRegisteredUser();
				for (UserEntity userEnt : users) {
					if (userEnt.getUserName().equals(username)) {
						tweets.setTweetId(tweetUpdatedId + 1);
						tweets.setUserId(userEnt.getUserId());
						return tweetsRepository.save(tweets);
					}
				}
				break;
			case MessageConstants.UPDATE_TWEET:
				List<TweetEntity> tweetlist1 = tweetsRepository.findAll();
				for (TweetEntity tweetValue : tweetlist1) {
					System.out.println(id + tweetValue.getTweetId() == id);
					if (tweetValue.getTweetId() == id) {
						tweetValue.setTweets(tweets.getTweets());
						return tweetsRepository.save(tweetValue);
					}
				}
				break;
			case MessageConstants.LIKE_TWEET:
				List<TweetEntity> tweetlist2 = tweetsRepository.findAll();
				for (TweetEntity tweetValue : tweetlist2) {
					System.out.println(id + tweetValue.getTweetId() != id);
					if (tweetValue.getTweetId() == id)
						tweetValue.setTweetLikes(tweets.getTweetLikes());
					return tweetsRepository.save(tweetValue);
				}
				break;
			case MessageConstants.REPLY_TWEET:
				List<TweetEntity> tweetlist3 = tweetsRepository.findAll();
				for (TweetEntity tweetValue : tweetlist3) {
					System.out.println(id + tweetValue.getTweetId() != id);
					if (tweetValue.getTweetId() == id)
						tweetValue.setReplyTweets(tweets.getReplyTweets());
					return tweetsRepository.save(tweetValue);
				}
				break;
			}
		} catch (Exception e) {
			throw new ErrorException(e.getMessage(), MessageConstants.SAVE_SERVICE_ERROR,
					MessageConstants.SAVE_TWEET_DETAILS);
		}
		return null;

	}

	@Override
	public void deleteTweetById(int id) throws ErrorException {
		try {
			tweetsRepository.deleteById(id);
		} catch (Exception e) {
			throw new ErrorException(e.getMessage(), MessageConstants.DELETE_SERVICE_ERROR,
					MessageConstants.DELETE_TWEET_BY_ID);
		}
	}

}
