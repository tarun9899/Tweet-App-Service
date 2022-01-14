package com.tweetapp.tweets.controller;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tweetapp.tweets.common.MessageServiceResponse;
import com.tweetapp.tweets.constants.MessageConstants;

import com.tweetapp.tweets.entity.TweetEntity;

import com.tweetapp.tweets.exception.ErrorException;
import com.tweetapp.tweets.service.TweetUserService;
import com.tweetapp.tweets.service.TweetsService;

@RestController
@RequestMapping("/api/v1.0/tweets/")
public class TweetsController {

	@Autowired
	public TweetsService tweetsService;

	@Autowired
	public TweetUserService tweetUserService;

	@Autowired
	public MessageServiceResponse messageService;
	
	@GetMapping(value = "/all")
	public ResponseEntity<?> getAllTweets() throws ErrorException {
		try {
			return ResponseEntity.ok().body(tweetsService.getALLTweets());
		} catch (Exception e) {
			throw new ErrorException(e.getMessage(), MessageConstants.FETCH_SERVICE_ERROR,
					MessageConstants.GET_ALL_TWEETS_BY_USER_NAME);
		}
	}

	@GetMapping(value = "/{username}")
	public ResponseEntity<?> getAllTweetsByUserName(@PathVariable String username) throws ErrorException {
		try {
			return ResponseEntity.ok().body(tweetsService.getTweetsByUserName(username));
		} catch (Exception e) {
			throw new ErrorException(e.getMessage(), MessageConstants.FETCH_SERVICE_ERROR,
					MessageConstants.GET_ALL_TWEETS_BY_USER_NAME);
		}
	}

	@PostMapping(value = "/{username}/add")
	public TweetEntity addTweet(@RequestBody TweetEntity tweets, @PathVariable String username) throws ErrorException {
		try {
			return tweetsService.saveTweetDetails(tweets, username, MessageConstants.ADD_TWEETS, 0);
		} catch (Exception e) {
			throw new ErrorException(e.getMessage(), MessageConstants.SAVE_SERVICE_ERROR, MessageConstants.ADD_TWEETS);
		}
	}

	@PutMapping(value = "/{username}/update/{id}")
	public TweetEntity updateTweet(@RequestBody TweetEntity tweets, @PathVariable String username, @PathVariable int id)
			throws ErrorException {
		try {
			return tweetsService.saveTweetDetails(tweets, username, MessageConstants.UPDATE_TWEET, id);
		} catch (Exception e) {
			throw new ErrorException(e.getMessage(), MessageConstants.SAVE_SERVICE_ERROR,
					MessageConstants.UPDATE_TWEET);
		}
	}

	@DeleteMapping(value = "/{username}/delete/{id}")
	public void deleteTweet(@PathVariable String username, @PathVariable int id) throws ErrorException {
		try {
			tweetsService.deleteTweetById(id);
		} catch (Exception e) {
			throw new ErrorException(e.getMessage(), MessageConstants.SAVE_SERVICE_ERROR,
					MessageConstants.DELETE_UPDATE);
		}
	}

	@PutMapping(value = "/{username}/like/{id}")
	public TweetEntity likeTweet(@RequestBody TweetEntity tweets, @PathVariable String username, @PathVariable int id)
			throws ErrorException {
		try {
			return tweetsService.saveTweetDetails(tweets, username, MessageConstants.LIKE_TWEET, id);
		} catch (Exception e) {
			throw new ErrorException(e.getMessage(), MessageConstants.SAVE_SERVICE_ERROR, MessageConstants.LIKE_TWEET);
		}

	}

	@PostMapping(value = "/{username}/reply/{id}")
	public TweetEntity replyTweet(@RequestBody TweetEntity tweets, @PathVariable String username, @PathVariable int id)
			throws ErrorException {
		try {
			return tweetsService.saveTweetDetails(tweets, username, MessageConstants.REPLY_TWEET, id);
		} catch (Exception e) {
			throw new ErrorException(e.getMessage(), MessageConstants.DELETE_SERVICE_ERROR,
					MessageConstants.REPLY_TWEET);
		}

	}

}
