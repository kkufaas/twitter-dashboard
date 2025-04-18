package com.example.twitterbackend;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/stats")
public class TweetStatsController {

    private final MongoTemplate mongoTemplate;

    @Autowired
    public TweetStatsController(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @GetMapping("/activity")
    public List<Document> getTweetActivityPerDay() {
        Aggregation aggregation = Aggregation.newAggregation(
                Aggregation.project()
                        .and(DateOperators.dateOf("Timestamp")
                                .toString("%Y-%m-%d"))
                        .as("date"),
                Aggregation.group("date").count().as("count"),
                Aggregation.sort(Sort.Direction.ASC, "_id")
        );

        AggregationResults<Document> results = mongoTemplate.aggregate(aggregation, "tweets", Document.class);
        return results.getMappedResults();
    }


    @GetMapping("/top-users")
    public List<Document> getTopUsersByLikes() {
        Aggregation aggregation = Aggregation.newAggregation(
                Aggregation.group("Username")     // Capital U
                        .sum("Likes").as("totalLikes"),  // Capital L
                Aggregation.sort(Sort.Direction.DESC, "totalLikes"),
                Aggregation.limit(10)
        );

        AggregationResults<Document> results = mongoTemplate.aggregate(aggregation, "tweets", Document.class);
        return results.getMappedResults();
    }


    @GetMapping("/test-one")
    public Document getOneTweet() {
        return mongoTemplate.findOne(
                new org.springframework.data.mongodb.core.query.Query(),
                Document.class,
                "tweets"
        );
    }



}
