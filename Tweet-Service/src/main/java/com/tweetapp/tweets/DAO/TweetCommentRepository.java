package com.tweetapp.tweets.DAO;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.tweetapp.tweets.entity.TweetCommentEnitiy;
import com.tweetapp.tweets.entity.TweetEntity;


@Repository
public class TweetCommentRepository{
 @Autowired
	private DynamoDBMapper dynamoDBMapper;
	
	
	public TweetCommentEnitiy tweetCommentSaveDetails(TweetCommentEnitiy userObject) {
		 dynamoDBMapper.save(userObject);
		 return userObject;
	}
	
	public TweetCommentEnitiy tweetCommentFindByIdDetails(TweetCommentEnitiy tweet) {
	return  dynamoDBMapper.load(TweetCommentEnitiy.class,tweet.getTweetCommentId());
	}
	
	public List<TweetCommentEnitiy> tweetCommentFindALLDetails() {
		return  dynamoDBMapper.scan(TweetCommentEnitiy.class, new DynamoDBScanExpression());
   }
	
	public void deleteById(TweetCommentEnitiy tweets) {
		 dynamoDBMapper.delete(tweets);	
	}
}
