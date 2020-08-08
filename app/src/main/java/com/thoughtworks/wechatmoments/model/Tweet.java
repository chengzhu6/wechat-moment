package com.thoughtworks.wechatmoments.model;

import com.thoughtworks.wechatmoments.repository.main.entity.TweetResponse;

public class Tweet extends BaseData {
    private String username;
    private String avatar;
    private String content;

    public String getUsername() {
        return username;
    }

    public String getAvatar() {
        return avatar;
    }

    public String getContent() {
        return content;
    }

    public static Tweet fromTweetResponse(TweetResponse tweetResponse) {
        Tweet tweet = new Tweet();
        tweet.username = tweetResponse.getSender().getUsername();
        tweet.avatar = tweetResponse.getSender().getAvatar();
        tweet.content = tweetResponse.getContent();
        return tweet;
    }
}
