package com.tweetapp.tweets.DAO;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.tweetapp.tweets.entity.TweetCommentEnitiy;

@Repository
public interface TweetCommentRepository extends MongoRepository<TweetCommentEnitiy,Integer> {

}
