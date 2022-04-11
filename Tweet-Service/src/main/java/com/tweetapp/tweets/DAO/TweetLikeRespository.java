package com.tweetapp.tweets.DAO;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.tweetapp.tweets.entity.TweetLikeEntity;

public interface TweetLikeRespository extends MongoRepository<TweetLikeEntity, Integer>{

}
