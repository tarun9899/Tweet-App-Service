package com.tweetapp.tweets.DAO;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.tweetapp.tweets.entity.TweetEntity;

@Repository
public interface TweetsRespository extends MongoRepository<TweetEntity, Integer> {

}
