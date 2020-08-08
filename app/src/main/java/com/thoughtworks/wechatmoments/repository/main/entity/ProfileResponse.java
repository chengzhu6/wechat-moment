package com.thoughtworks.wechatmoments.repository.main.entity;

import com.google.gson.annotations.SerializedName;

public class ProfileResponse {

    @SerializedName("profile-image")
    private String profileImage;
    private String avatar;
    private String username;

    public ProfileResponse() {
    }

    public String getProfileImage() {
        return profileImage;
    }

    public String getAvatar() {
        return avatar;
    }

    public String getUsername() {
        return username;
    }
}
