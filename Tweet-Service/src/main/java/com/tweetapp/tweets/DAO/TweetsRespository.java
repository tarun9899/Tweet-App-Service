package com.tweetapp.tweets.DAO;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.tweetapp.tweets.entity.TweetEntity;
import com.tweetapp.tweets.entity.UserEntity;


@Repository
public class TweetsRespository {
	
    @Autowired
	private DynamoDBMapper dynamoDBMapper;
	
	
	public TweetEntity tweetSaveDetails(TweetEntity userObject) {
		 dynamoDBMapper.save(userObject);
		 return userObject;
	}
	
	public TweetEntity tweetFindByIdDetails(UserEntity tweet) {
	return  dynamoDBMapper.load(TweetEntity.class,tweet.getUserId());
	}
	
	public List<TweetEntity> tweetFindALLDetails() {
		return  dynamoDBMapper.scan(TweetEntity.class, new DynamoDBScanExpression());
   }
	
	public void deleteById(TweetEntity tweets) {
		 dynamoDBMapper.delete(tweets);	
	}
}

