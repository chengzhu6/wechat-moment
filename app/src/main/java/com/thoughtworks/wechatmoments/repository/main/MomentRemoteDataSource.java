package com.thoughtworks.wechatmoments.repository.main;

import com.thoughtworks.wechatmoments.repository.main.entity.ProfileResponse;
import com.thoughtworks.wechatmoments.repository.main.entity.TweetResponse;
import com.thoughtworks.wechatmoments.util.HttpUtil;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Maybe;

public class MomentRemoteDataSource implements MomentDataSource {
    private static final String PROFILE_URL = "https://twc-android-bootcamp.github.io/fake-data/data/weixin/profile.json";
    private static final String TWEETS_URL = "https://twc-android-bootcamp.github.io/fake-data/data/weixin/tweets.json";

    @Override
    public Maybe<ProfileResponse> getProfile() {
        return Maybe.create(emitter -> {
            ProfileResponse profileResponse = HttpUtil.get(PROFILE_URL, ProfileResponse.class);
            emitter.onSuccess(profileResponse);
        });
    }

    @Override
    public Maybe<List<TweetResponse>> getTweets() {
        return Maybe.create(emitter -> {
            ArrayList<TweetResponse> tweets = HttpUtil.getList(TWEETS_URL, TweetResponse.class);
            emitter.onSuccess(tweets);
        });
    }
}
