package com.example.twitterbackend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tweets")
public class TweetController {

    private final TweetRepository tweetRepository;

    @Autowired
    public TweetController(TweetRepository tweetRepository) {
        this.tweetRepository = tweetRepository;
    }

    @GetMapping
    public List<Tweet> getAllTweets() {
        return tweetRepository.findAll();
    }
}
