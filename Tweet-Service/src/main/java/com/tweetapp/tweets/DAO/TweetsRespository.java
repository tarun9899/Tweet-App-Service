package com.tweetapp.tweets.DAO;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
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
	
//	public List<TweetEntity> tweetFindByIdDetails(UserEntity tweet) {
//		  Map<String,String> expressionAttributesNames = new HashMap<>();
//	        expressionAttributesNames.put("#userid",Integer.toString(tweet.getUserId()));	 
//		Map<String, AttributeValue> expressionAttributeValues = new HashMap<String, AttributeValue>();
//		expressionAttributeValues.put(":useridvalue",new AttributeValue().withS("userid"));
//		  DynamoDBQueryExpression<TweetEntity> queryExpression = new DynamoDBQueryExpression<TweetEntity>()
//		          .withKeyConditionExpression("userid = :useridvalue")
//		          .withExpressionAttributeNames(expressionAttributesNames)
//	                .withExpressionAttributeValues(expressionAttributeValues);
//       return  dynamoDBMapper.query(TweetEntity.class,queryExpression);
//	}
	
	public List<TweetEntity> tweetFindALLDetails() {
		return  dynamoDBMapper.scan(TweetEntity.class, new DynamoDBScanExpression());
   }
	
	public void deleteById(TweetEntity tweets) {
		 dynamoDBMapper.delete(tweets);	
	}
}

