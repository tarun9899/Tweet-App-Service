package com.tweetapp.tweets.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.tweetapp.tweets.DAO.TweetCommentRepository;
import com.tweetapp.tweets.DAO.TweetLikeRespository;
import com.tweetapp.tweets.DAO.TweetUserRepository;
import com.tweetapp.tweets.DAO.TweetsRespository;

import com.tweetapp.tweets.constants.MessageConstants;
import com.tweetapp.tweets.entity.TweetCommentEnitiy;
import com.tweetapp.tweets.entity.TweetEntity;
import com.tweetapp.tweets.entity.TweetLikeEntity;
import com.tweetapp.tweets.entity.UserEntity;
import com.tweetapp.tweets.exception.ErrorException;

@Service
public class TweetsServiceImpl implements TweetsService {

	@Autowired
	public TweetsRespository tweetsRepository;

	@Autowired
	public TweetUserRepository userRepository;

	@Autowired
	public TweetLikeRespository likeRepository;

	@Autowired
	public TweetUserService tweetUserService;

	@Autowired
	public TweetCommentRepository commentRepository;

	@Override
	public List<TweetEntity> getALLTweets() throws ErrorException {
		try {
			return tweetsRepository.tweetFindALLDetails();
		} catch (Exception e) {
			throw new ErrorException(e.getMessage(), MessageConstants.FETCH_SERVICE_ERROR,
					MessageConstants.GET_ALL_TWEETS);
		}

	}

	@Override
	public List<TweetEntity> getTweetsByUserName(String username) throws ErrorException {

		try {
			List<UserEntity> userList = tweetUserService.getALLRegisteredUser();
			for (UserEntity users : userList) {
				if (users.getUserName().equals(username)) {
					return tweetsRepository.tweetFindALLDetails();
				}
				else {
					List<TweetEntity> array = new ArrayList<>();
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
			List<TweetEntity> tweetlist = tweetsRepository.tweetFindALLDetails();
			for (TweetEntity tweetValue : tweetlist) {
				tweetUpdatedId = tweetlist.size();
			}
			switch (action) {
			case MessageConstants.ADD_TWEETS:
				List<UserEntity> users = tweetUserService.getALLRegisteredUser();
				for (UserEntity userEnt : users) {
					if (userEnt.getUserName().equals(username)) {
						tweets.setTweetId(tweetUpdatedId + 1);
						tweets.setUserId(userEnt.getUserId());
						return tweetsRepository.tweetSaveDetails(tweets);
					}
				}
				break;
			case MessageConstants.UPDATE_TWEET:
				List<TweetEntity> tweetlist1 = tweetsRepository.tweetFindALLDetails();
				for (TweetEntity tweetValue : tweetlist1) {
					System.out.println(id + tweetValue.getTweetId() == id);
					if (tweetValue.getTweetId() == id) {
						tweetValue.setTweets(tweets.getTweets());
						return tweetsRepository.tweetSaveDetails(tweets);
					}
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
	public void deleteTweetById(int id,String tweet) throws ErrorException {
		try {
			int tweetCommentID = 0;
			List<TweetCommentEnitiy> tweetCommValue = commentRepository.tweetCommentFindALLDetails();
			for (TweetCommentEnitiy tweetComVal : tweetCommValue) {
//				List<TweetEntity> tweetsValues1 = tweetsRepository.tweetFindALLDetails();
//				for (TweetEntity tweetValuess : tweetsValues1) {
					if (tweet.equals(tweetComVal.getComment())) {
						tweetCommentID = tweetComVal.getTweetId();
						commentRepository.deleteById(tweetComVal);
					//}
					
				}
			}

			List<TweetLikeEntity> tweetLikeValue = likeRepository.tweetLikeFindALLDetails();
			for (TweetLikeEntity tweetLikeVal : tweetLikeValue) {
				if (tweetLikeVal.getTweetId() == id) {
					likeRepository.deleteById(tweetLikeVal);
				}
			}
			List<TweetEntity> tweetsValues = tweetsRepository.tweetFindALLDetails();
			for (TweetEntity tweetValue : tweetsValues) {
				if (tweetValue.getTweetId() == id) {
					tweetsRepository.deleteById(tweetValue);
					
				}
				if (tweetValue.getTweetId() == tweetCommentID) {
					tweetValue.setCommentTweetsCount(tweetValue.getCommentTweetsCount() - 1);
					tweetsRepository.tweetSaveDetails(tweetValue);
				}
			}
		} catch (Exception e) {
			throw new ErrorException(e.getMessage(), MessageConstants.DELETE_SERVICE_ERROR,
					MessageConstants.DELETE_TWEET_BY_ID);
		}
	}

	@Override
	public TweetLikeEntity saveLikeTweetDetails(TweetLikeEntity tweetsLikes, String username, int id)
			throws ErrorException {
		int tweetLikeId = 0;
		int tweetunlikeId = 0;
		int tweetLikeCount = 0;
		int tweetDislikeCount = 0;
		boolean userAlreadyExistsFlag = false;
		List<TweetLikeEntity> tweetLikeList = likeRepository.tweetLikeFindALLDetails();
		for (TweetLikeEntity tweetLikeValue : tweetLikeList) {
			tweetLikeId = tweetLikeList.size();
			if (tweetLikeValue.getUserName().equals(username) && tweetLikeValue.getTweetId() == id) {
				userAlreadyExistsFlag = true;
				tweetunlikeId = tweetLikeValue.getTweetLiketId();
			}
		}
		try {
			if (tweetsLikes.isTweetLikes() == true) {
				if (!userAlreadyExistsFlag) {
					tweetsLikes.setTweetId(id);
					tweetsLikes.setTweetLiketId(tweetLikeId + 1);
					tweetsLikes.setUserName(username);
					likeRepository.tweetLikeSaveDetails(tweetsLikes);
					List<TweetLikeEntity> tweetLikeList1 = likeRepository.tweetLikeFindALLDetails();
					for (TweetLikeEntity likeValue : tweetLikeList1) {
						if (likeValue.getTweetId() == id) {
							tweetLikeCount = tweetLikeCount + 1;
						}
						System.out.println(tweetLikeCount);
					}

					List<TweetEntity> tweetlist = tweetsRepository.tweetFindALLDetails();
					for (TweetEntity tweetValue : tweetlist) {
						if (tweetValue.getTweetId() == id) {
							System.out.println(tweetLikeCount);
							tweetValue.setLikeTweetsCount(tweetLikeCount);
							tweetsRepository.tweetSaveDetails(tweetValue);
						}

					}
					return tweetsLikes;
				}
			} else {
				// likeRepository.deleteById(tweetunlikeId);
				List<TweetLikeEntity> tweetLikeValue = likeRepository.tweetLikeFindALLDetails();
				for (TweetLikeEntity tweetLikeVal : tweetLikeValue) {
					if (tweetLikeVal.getTweetId() == tweetunlikeId) {
						likeRepository.deleteById(tweetLikeVal);
					}
				}
				List<TweetLikeEntity> tweetLikeList1 = likeRepository.tweetLikeFindALLDetails();
				for (TweetLikeEntity likeValue : tweetLikeList1) {
					if (likeValue.getTweetId() == id) {
						tweetDislikeCount = tweetDislikeCount + 1;
					}
					System.out.println(tweetDislikeCount);
				}

				List<TweetEntity> tweetlist = tweetsRepository.tweetFindALLDetails();
				for (TweetEntity tweetValue : tweetlist) {
					if (tweetValue.getTweetId() == id) {
						System.out.println(tweetDislikeCount);
						tweetValue.setLikeTweetsCount(tweetDislikeCount);
						tweetsRepository.tweetSaveDetails(tweetValue);
					}

				}
				return tweetsLikes;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public TweetCommentEnitiy saveCommentTweetDetails(TweetCommentEnitiy tweetsComment, String username, int id) {
		int tweetCommentedId = 0;
		int tweetUnCommentedId = 0;
		int tweetCommentCount = 0;
		int tweetUnCommentCount = 0;
		boolean userAlreadyExistsFlag = false;
		List<TweetCommentEnitiy> tweetCommentList = commentRepository.tweetCommentFindALLDetails();
		for (TweetCommentEnitiy tweetCommentValue : tweetCommentList) {
			tweetCommentedId = 0;
			tweetCommentedId = tweetCommentList.size();
			System.out.println(tweetCommentValue.getUserName() + " - " + tweetCommentValue.getTweetId());
//			if (tweetCommentValue.getUserName().equals(username) && tweetCommentValue.getTweetId() == id) {
//				userAlreadyExistsFlag = true;
//				tweetUnCommentedId = tweetCommentValue.getTweetCommentId();
//				break;
//			}
		}
		try {
			if (tweetsComment.getComment() != null) {
				if (!userAlreadyExistsFlag) {
					tweetsComment.setTweetId(id);
					tweetsComment.setTweetCommentId(tweetCommentedId + 1);
					tweetsComment.setUserName(username);
					commentRepository.tweetCommentSaveDetails(tweetsComment);
					System.out.println("count" + tweetsComment);
					List<TweetCommentEnitiy> tweetCommentList1 = commentRepository.tweetCommentFindALLDetails();
					for (TweetCommentEnitiy tweetCommentValue : tweetCommentList1) {
						if (tweetCommentValue.getTweetId() == id) {
							tweetCommentCount = tweetCommentCount + 1;
							System.out.println("count1comment" + tweetCommentCount);
						}
					}
					List<TweetEntity> tweetList = tweetsRepository.tweetFindALLDetails();
					for (TweetEntity tweetValue : tweetList) {
						if (tweetValue.getTweetId() == id) {
							System.out.println(tweetCommentCount);
							tweetValue.setCommentTweetsCount(tweetCommentCount);
							System.out.println("count2com" + tweetCommentCount);
							tweetsRepository.tweetSaveDetails(tweetValue);
						}
					}
					return tweetsComment;
				}

			} else {
				// commentRepository.deleteById(tweetUnCommentedId);
				List<TweetCommentEnitiy> tweetCommValue = commentRepository.tweetCommentFindALLDetails();
				for (TweetCommentEnitiy tweetVal : tweetCommValue) {
					if (tweetVal.getTweetId() == id) {
						commentRepository.deleteById(tweetVal);
					}
				}
				List<TweetCommentEnitiy> tweetCommentList1 = commentRepository.tweetCommentFindALLDetails();
				for (TweetCommentEnitiy tweetCommentValue : tweetCommentList1) {
					if (tweetCommentValue.getTweetId() == id) {
						tweetUnCommentCount = tweetUnCommentCount + 1;
					}
				}
				List<TweetEntity> tweetList = tweetsRepository.tweetFindALLDetails();
				for (TweetEntity tweetValue : tweetList) {
					if (tweetValue.getTweetId() == id) {
						System.out.println("final" + tweetUnCommentCount);
						tweetValue.setCommentTweetsCount(tweetUnCommentCount);
						tweetsRepository.tweetSaveDetails(tweetValue);
					}
				}
				return tweetsComment;

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<TweetLikeEntity> getALLLikeTweets() throws ErrorException {
		try {
			return likeRepository.tweetLikeFindALLDetails();

		} catch (Exception e) {
			throw new ErrorException(e.getMessage(), MessageConstants.FETCH_SERVICE_ERROR,
					MessageConstants.GET_ALL_TWEETS);
		}
	}

	@Override
	public List<TweetCommentEnitiy> getALLCommentsTweets() throws ErrorException {
		try {
			return commentRepository.tweetCommentFindALLDetails();

		} catch (Exception e) {
			throw new ErrorException(e.getMessage(), MessageConstants.FETCH_SERVICE_ERROR,
					MessageConstants.GET_ALL_TWEETS);
		}
	}

}
