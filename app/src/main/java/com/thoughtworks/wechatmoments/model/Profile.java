package com.thoughtworks.wechatmoments.model;

import com.thoughtworks.wechatmoments.repository.main.entity.ProfileResponse;
import com.thoughtworks.wechatmoments.repository.main.entity.TweetResponse;

public class Profile extends BaseData{
    public String getName() {
        return name;
    }

    public String getAvatar() {
        return avatar;
    }

    public String getBackgroundImage() {
        return backgroundImage;
    }

    private String name;
    private String avatar;
    private String backgroundImage;

    public static Profile from(ProfileResponse profileResponse) {
        Profile profile = new Profile();
        profile.name = profileResponse.getUsername();
        profile.avatar = profileResponse.getAvatar();
        profile.backgroundImage = profileResponse.getProfileImage();
        return profile;
    }

    public static Profile createFromSender(TweetResponse.SenderBean senderBean) {
        Profile profile = new Profile();
        profile.name = senderBean.getUsername();
        profile.avatar = senderBean.getAvatar();
        return profile;
    }
}
