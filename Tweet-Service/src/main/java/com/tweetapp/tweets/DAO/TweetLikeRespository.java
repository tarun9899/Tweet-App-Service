package com.tweetapp.tweets.DAO;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.tweetapp.tweets.entity.TweetEntity;
import com.tweetapp.tweets.entity.TweetLikeEntity;

@Repository
public class TweetLikeRespository{
  @Autowired
	private DynamoDBMapper dynamoDBMapper;
	
	
	public TweetLikeEntity tweetLikeSaveDetails(TweetLikeEntity userObject) {
		 dynamoDBMapper.save(userObject);
		 return userObject;
	}
	
	public TweetLikeEntity tweetLikeFindByIdDetails(TweetLikeEntity tweet) {
	return  dynamoDBMapper.load(TweetLikeEntity.class,tweet.getTweetLiketId());
	}
	
	public List<TweetLikeEntity> tweetLikeFindALLDetails() {
		return  dynamoDBMapper.scan(TweetLikeEntity.class, new DynamoDBScanExpression());
   }
	
	public void deleteById(TweetLikeEntity tweets) {
		 dynamoDBMapper.delete(tweets);	
	}
}
