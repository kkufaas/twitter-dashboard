package com.example.twitterbackend;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@Document(collection = "tweets")
public class Tweet {

    @Id
    private String id; // MongoDB document ID

    private String Tweet_ID;
    private String Username;
    private String Text;
    private int Retweets;
    private int Likes;
    private LocalDateTime Timestamp;
}
