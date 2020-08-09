package com.thoughtworks.wechatmoments.model;

import com.thoughtworks.wechatmoments.repository.main.entity.TweetResponse;

import java.util.List;

public class Tweet extends BaseData {
    private String username;
    private String avatar;
    private String content;
    private List<String> imagesPath;

    public String getUsername() {
        return username;
    }

    public String getAvatar() {
        return avatar;
    }

    public List<String> getImagesPath() {
        return imagesPath;
    }

    public String getContent() {
        return content;
    }

    public static Tweet fromTweetResponse(TweetResponse tweetResponse) {
        Tweet tweet = new Tweet();
        tweet.username = tweetResponse.getSender().getUsername();
        tweet.avatar = tweetResponse.getSender().getAvatar();
        tweet.content = tweetResponse.getContent();
        tweet.imagesPath = tweetResponse.getImages();
        return tweet;
    }

    public boolean isValid() {
        return this.content != null || this.imagesPath != null;
    }
}
