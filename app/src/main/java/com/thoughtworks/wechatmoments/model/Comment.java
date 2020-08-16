package com.thoughtworks.wechatmoments.model;

import com.thoughtworks.wechatmoments.repository.main.entity.TweetResponse;

public class Comment extends BaseData{
    private String content;
    private Profile sender;

    public static Comment createFrom(TweetResponse.CommentResponse commentResponse) {
        Comment comment = new Comment();
        comment.content= commentResponse.getContent();
        comment.sender = Profile.createFromSender(commentResponse.getSender());
        return comment;
    }

    public String getContent() {
        return content;
    }

    public String getSenderName() {
        return sender.getName();
    }

}
