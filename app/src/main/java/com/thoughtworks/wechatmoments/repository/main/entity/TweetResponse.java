package com.thoughtworks.wechatmoments.repository.main.entity;

import androidx.annotation.NonNull;

import java.util.List;

public class TweetResponse {

    private SenderBean sender;

    private String content;

    private List<String> images;

    private List<CommentResponse> comments;

    public String getContent() {
        return content;
    }

    public SenderBean getSender() {
        return sender;
    }

    public List<String> getImages() {
        return images;
    }

    public List<CommentResponse> getComments() {
        return comments;
    }

    public static class SenderBean {
        private String username;
        private String nick;
        private String avatar;

        public String getUsername() {
            return username;
        }

        public String getNick() {
            return nick;
        }

        public String getAvatar() {
            return avatar;
        }

    }
    public static class CommentResponse {
        private String content;
        private SenderBean sender;

        public String getContent() {
            return content;
        }

        public SenderBean getSender() {
            return sender;
        }
    }
}
