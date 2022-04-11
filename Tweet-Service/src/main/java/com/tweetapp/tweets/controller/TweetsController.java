package com.tweetapp.tweets.controller;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tweetapp.tweets.DTO.MessageDTO;
import com.tweetapp.tweets.common.MessageServiceResponse;
import com.tweetapp.tweets.constants.MessageConstants;
import com.tweetapp.tweets.entity.TweetCommentEnitiy;
import com.tweetapp.tweets.entity.TweetEntity;
import com.tweetapp.tweets.entity.TweetLikeEntity;
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
	
	@CrossOrigin(origins="http://localhost:4200")
	@GetMapping(value = "/all")
	public ResponseEntity<?> getAllTweets() throws ErrorException {
		try {
			return ResponseEntity.ok().body(tweetsService.getALLTweets());
		} catch (Exception e) {
			throw new ErrorException(e.getMessage(), MessageConstants.FETCH_SERVICE_ERROR,
					MessageConstants.GET_ALL_TWEETS_BY_USER_NAME);
		}
	}

	@CrossOrigin(origins="http://localhost:4200")
	@GetMapping(value = "/{username}")
	public ResponseEntity<?> getAllTweetsByUserName(@PathVariable String username) throws ErrorException {
		try {
			return ResponseEntity.ok().body(tweetsService.getTweetsByUserName(username));
		} catch (Exception e) {
			throw new ErrorException(e.getMessage(), MessageConstants.FETCH_SERVICE_ERROR,
					MessageConstants.GET_ALL_TWEETS_BY_USER_NAME);
		}
	}

	@CrossOrigin(origins="http://localhost:4200")
	@PostMapping(value = "/{username}/add")
	public TweetEntity addTweet(@RequestBody TweetEntity tweets, @PathVariable String username) throws ErrorException {
		try {
			return tweetsService.saveTweetDetails(tweets, username, MessageConstants.ADD_TWEETS, 0);
		} catch (Exception e) {
			throw new ErrorException(e.getMessage(), MessageConstants.SAVE_SERVICE_ERROR, MessageConstants.ADD_TWEETS);
		}
	}

	@CrossOrigin(origins="http://localhost:4200")
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

	@CrossOrigin(origins="http://localhost:4200")
	@DeleteMapping(value = "/{username}/delete/{id}")
	public MessageDTO deleteTweet(@PathVariable String username, @PathVariable int id) throws ErrorException {
		MessageDTO message = new MessageDTO();
		try {
			tweetsService.deleteTweetById(id);
			message.setSuccessMessage("Tweet Deleted Successfully");
			message.setMessageCode(200);
		} catch (Exception e) {
			throw new ErrorException(e.getMessage(), MessageConstants.SAVE_SERVICE_ERROR,
					MessageConstants.DELETE_UPDATE);
		}
		return message;
	}

	@CrossOrigin(origins="http://localhost:4200")
	@PutMapping(value = "/{username}/like/{id}")
	public TweetLikeEntity likeTweet(@RequestBody TweetLikeEntity tweetsLikes, @PathVariable String username, @PathVariable int id)
			throws ErrorException {
		try {
			return tweetsService.saveLikeTweetDetails(tweetsLikes, username,id);
		} catch (Exception e) {
			throw new ErrorException(e.getMessage(), MessageConstants.SAVE_SERVICE_ERROR, MessageConstants.LIKE_TWEET);
		}

	}

	@CrossOrigin(origins="http://localhost:4200")
	@PostMapping(value = "/{username}/reply/{id}")
	public TweetCommentEnitiy replyTweet(@RequestBody TweetCommentEnitiy tweets, @PathVariable String username, @PathVariable int id)
			throws ErrorException {
		try {
			return tweetsService.saveCommentTweetDetails(tweets, username, id);
		} catch (Exception e) {
			throw new ErrorException(e.getMessage(), MessageConstants.DELETE_SERVICE_ERROR,
					MessageConstants.REPLY_TWEET);
		}

	}
	
	@CrossOrigin(origins="http://localhost:4200")
	@GetMapping(value = "/likes/all")
	public ResponseEntity<?> getAllTweetsBylike() throws ErrorException {
		try {
			return ResponseEntity.ok().body(tweetsService.getALLLikeTweets());
		} catch (Exception e) {
			throw new ErrorException(e.getMessage(), MessageConstants.FETCH_SERVICE_ERROR,
					MessageConstants.GET_ALL_TWEETS_BY_USER_NAME);
		}
	}


}
