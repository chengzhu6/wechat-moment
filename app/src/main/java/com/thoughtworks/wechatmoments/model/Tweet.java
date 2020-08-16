package com.thoughtworks.wechatmoments.model;

import android.os.Build;

import androidx.annotation.RequiresApi;

import com.thoughtworks.wechatmoments.repository.main.entity.TweetResponse;


import java.util.List;
import java.util.stream.Collectors;

public class Tweet extends BaseData {
    private String username;
    private String avatar;
    private String content;
    private List<String> imagesPath;

    public List<Comment> getComments() {
        return comments;
    }

    private List<Comment> comments;

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

    @RequiresApi(api = Build.VERSION_CODES.N)
    public static Tweet fromTweetResponse(TweetResponse tweetResponse) {
        Tweet tweet = new Tweet();
        tweet.username = tweetResponse.getSender().getUsername();
        tweet.avatar = tweetResponse.getSender().getAvatar();
        tweet.content = tweetResponse.getContent();
        tweet.imagesPath = tweetResponse.getImages();
        if (tweetResponse.getComments() != null) {
            tweet.comments = tweetResponse.getComments().stream()
                    .map(Comment::createFrom)
                    .collect(Collectors.toList());
        }
        return tweet;
    }

    public boolean isValid() {
        return this.content != null || this.imagesPath != null;
    }
}
