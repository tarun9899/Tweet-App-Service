package com.tweetapp.tweets.DAO;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.tweetapp.tweets.entity.UserEntity;

@Repository
public interface TweetUserRepository extends MongoRepository<UserEntity,Integer> {
	
}
