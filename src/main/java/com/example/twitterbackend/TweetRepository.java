package com.example.twitterbackend;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface TweetRepository extends MongoRepository<Tweet, String> {
}