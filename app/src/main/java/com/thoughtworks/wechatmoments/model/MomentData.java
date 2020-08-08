package com.thoughtworks.wechatmoments.model;

import com.thoughtworks.wechatmoments.repository.main.entity.ProfileResponse;

public class MomentData {

    private MomentDataType type;

    private BaseData data;

    public MomentDataType getType() {
        return type;
    }

    public BaseData getData() {
        return data;
    }

    public static MomentData createProfileMomentData(ProfileResponse profileResponse) {
        MomentData momentData = new MomentData();
        momentData.type = MomentDataType.PROFILE;
        momentData.data = Profile.from(profileResponse);
        return momentData;
    }

    public static MomentData fromTweet(Tweet tweet) {
        MomentData momentData = new MomentData();
        momentData.type = MomentDataType.TWEET;
        momentData.data = tweet;
        return momentData;
    }

    public enum MomentDataType {
        PROFILE(0x00), TWEET(0x01);

        private final int value;

        public int getValue() {
            return value;
        }

        MomentDataType(int value) {
            this.value = value;
        }
    }
}
